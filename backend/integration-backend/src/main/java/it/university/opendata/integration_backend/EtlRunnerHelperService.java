package it.university.opendata.integration_backend;

import it.university.opendata.integration_backend.dto.inail.InfortunioXmlDTO;
import it.university.opendata.integration_backend.entity.InfortuniInail;
import it.university.opendata.integration_backend.repositories.inail.InfortuniInailRepository;
import it.university.opendata.integration_backend.mapper.inail.InailMapper;
import it.university.opendata.integration_backend.reader.InailXmlClasspathReader;
import it.university.opendata.integration_backend.dto.inps.InpsJsonRecord;
import it.university.opendata.integration_backend.entity.PensioniInps;
import it.university.opendata.integration_backend.repositories.inps.PensioniInpsRepository;
import it.university.opendata.integration_backend.util.CategoriaInfortunio;
import it.university.opendata.integration_backend.util.proiezioni.KeySum;
import it.university.opendata.integration_backend.mapper.inps.InpsMapper;
import it.university.opendata.integration_backend.reader.InpsJsonClasspathReader;
import it.university.opendata.integration_backend.util.CategoriaPensione;
import it.university.opendata.integration_backend.util.ClasseEta;
import it.university.opendata.integration_backend.util.Regioni;
import it.university.opendata.integration_backend.util.UtilMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Component
public class EtlRunnerHelperService {

    private static final Logger logger = LoggerFactory.getLogger(EtlRunnerHelperService.class);

    private static final String SLASH = "/";

    @Autowired
    private InpsJsonClasspathReader inpsReader;

    @Autowired
    private InailXmlClasspathReader inailReader;

    @Autowired
    private InpsMapper inpsMapper;

    @Autowired
    private InailMapper inailMapper;

    @Autowired
    private PensioniInpsRepository inpsRepository;

    @Autowired
    private InfortuniInailRepository inailRepository;

    /**
     * Esegue il processo ETL (Extract, Transform, Load) per i dataset INPS e INAIL
     * presenti nel classpath, caricando e salvando i dati nel database.
     * <p>
     * Il metodo:
     * Legge il file JSON delle pensioni INPS e lo mappa in entità PensioniInps
     * Legge i file XML degli infortuni INAIL per le regioni Lombardia, Lazio e Calabria
     * Mappa i dati in entità InfortuniInail
     * Salva tutte le entità nei rispettivi repository
     * <p>
     * In caso di file mancanti o vuoti, viene loggato un errore e l'elaborazione si interrompe.
     *
     * @throws IOException se si verifica un errore durante la lettura dei file dal classpath
     */
    void caricaDataset(int anno, String trimestre, List<String> regioniList) throws IOException {

        String path = "datasets/" + anno + SLASH + UtilMapping.romanToTrimestre(trimestre) + SLASH;

        //Caricamento dati INPS
        List<InpsJsonRecord> pensioniItalia = inpsReader.readFromClasspath(path + "inps/pensioniItalia.json");
        if (pensioniItalia == null) {
            logger.error("ATTENZIONE: File JSON 'pensioniItalia' non trovato o vuoto");
            return;
        }

        //Aggrega e mappa i dati in PensioniInps Entity
        List<PensioniInps> entitiesPensioni = inpsMapper.getPensioniInps(pensioniItalia);
        if (entitiesPensioni == null || entitiesPensioni.isEmpty()) {
            logger.error("ATTENZIONE: ");
            return;
        }

        //Salva in PENSIONI_INPS
        inpsRepository.saveAll(entitiesPensioni);
        logger.info(String.format("ETL INPS completato: %s record caricati.", entitiesPensioni.size()));

        //Caricamento dati INAIL
        for (String regione : regioniList) {
            String regioneNormalizzata = UtilMapping.normalizzaDescRegione(regione);
            String resource = path + "inail/Infortuni_" + regioneNormalizzata + ".xml";
            List<InfortunioXmlDTO> infortuni = inailReader.readFromClasspath(resource);
            if (infortuni == null || infortuni.isEmpty()) {
                logger.warn("ATTENZIONE: File XML '{}' non trovato o vuoto. Procedo con la prossima regione.", resource);
                continue; // vai avanti senza interrompere il ciclo
            }

            //Salva in INFORTUNI_INAIL
            List<InfortuniInail> entitiesInail = inailMapper.getInfortuniInail(infortuni, anno, trimestre, regione);
            inailRepository.saveAll(entitiesInail);
            logger.info(String.format("ETL INAIL %s completato: %s record caricati.", regione, entitiesInail.size()));
        }
    }

    /**
     * Esegue un’analisi sintetica dei dati INPS per l’anno e il trimestre indicati
     * e scrive nei log i seguenti indicatori:
     * <p>
     * Totale pensioni erogate
     * Totale per sesso
     * Totale per classe d’età
     * Totale per categoria di pensione
     * Totale per regione
     * <p>
     * La metodologia è: query aggregate sul repository, eventuale mapping delle chiavi tramite
     * {ClasseEta.getDescrizioneFromCodice(...)} e {CategoriaPensione.getDescrizioneFromCodice(...)}
     *
     * @param anno      anno di riferimento (es. 2024)
     * @param trimestre trimestre di riferimento (es. "I", "II", "III", "IV")
     */
    void getAnalisiDatasetInps(int anno, String trimestre) {

        Long totalePensioni = inpsRepository.sumNumPensioniByAnnoAndTrimestre(anno, trimestre);
        logger.info("Nel {} trimestre del {}, numero totale pensioni: {}", trimestre, anno, totalePensioni);

        //Sesso
        List<KeySum> totalePensioniPerSesso = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndSesso(anno, trimestre);
        for (KeySum sesso : totalePensioniPerSesso) {
            String sessoDesc = sesso.getKey().equals("F") ? "femminile" : "maschile";
            logger.info("Nel {} trimestre del {}, numero totale pensioni per sesso {} : {}", trimestre, anno, sessoDesc, sesso.getTotale());
        }

        KeySum maxResultSesso = getMaxPerTotalePensioniPerTipo(totalePensioniPerSesso);
        String sessoDescMax = maxResultSesso.getKey().equals("F") ? "femminile" : "maschile";
        logger.info("Nel {} trimestre del {}, il sesso con piu' pensioni e' {} con {}", trimestre, anno, sessoDescMax, maxResultSesso.getTotale());

        //ClasseEta
        List<KeySum> totalePensioniPerClasseEta = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndClasseEta(anno, trimestre);
        for (KeySum classeEta : totalePensioniPerClasseEta) {
            String classeEtaDesc = ClasseEta.getDescrizioneFromCodice((Integer) classeEta.getKey());
            logger.info("Nel {} trimestre del {}, numero totale pensioni per classeEta {} : {}", trimestre, anno, classeEtaDesc, classeEta.getTotale());
        }

        KeySum maxResultClasseEta = getMaxPerTotalePensioniPerTipo(totalePensioniPerClasseEta);
        String classeEtaDesc = ClasseEta.getDescrizioneFromCodice((Integer) maxResultClasseEta.getKey());
        logger.info("Nel {} trimestre del {}, la classeEta con piu' pensioni e' {} con {}", trimestre, anno, classeEtaDesc, maxResultClasseEta.getTotale());

        //CategoriaPensione
        List<KeySum> totalePensioniPerCategoriaPensione = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndCategoriaPensione(anno, trimestre);
        for (KeySum categoriaPensione : totalePensioniPerCategoriaPensione) {
            String categoriaPensioneDesc = CategoriaPensione.getDescrizioneFromCodice((String) categoriaPensione.getKey());
            logger.info("Nel {} trimestre del {}, numero totale pensioni per categoriaPensione {} : {}", trimestre, anno, categoriaPensioneDesc, categoriaPensione.getTotale());
        }

        KeySum maxResultCategoriaPensione = getMaxPerTotalePensioniPerTipo(totalePensioniPerCategoriaPensione);
        String categoriaPensioneDescMax = CategoriaPensione.getDescrizioneFromCodice((String) maxResultCategoriaPensione.getKey());
        logger.info("Nel {} trimestre del {}, la categoriaPensione con piu' pensioni e' {} con {}", trimestre, anno, categoriaPensioneDescMax, maxResultCategoriaPensione.getTotale());

        //Regione
        List<KeySum> totalePensioniPerRegione = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndRegione(anno, trimestre);
        for (KeySum regione : totalePensioniPerRegione) {
            String regioneDesc = Regioni.getDescrizioneFromCodice((String) regione.getKey());
            logger.info("Nel {} trimestre del {}, numero totale pensioni per regione {} : {}", trimestre, anno, regioneDesc, regione.getTotale());
        }

        KeySum maxResultRegione = getMaxPerTotalePensioniPerTipo(totalePensioniPerRegione);
        String regioneDescMax = Regioni.getDescrizioneFromCodice((String) maxResultRegione.getKey());
        logger.info("Nel {} trimestre del {}, il sesso con piu' pensioni e' {} con {}", trimestre, anno, regioneDescMax, maxResultRegione.getTotale());
    }

    void getAnalisiDatasetInail(int anno, String trimestre) {

        Long totaleInfortuni = inailRepository.sumNumInfortuniByAnnoAndTrimestre(anno, trimestre);
        logger.info("Nel {} trimestre del {}, numero totale infortuni: {}", trimestre, anno, totaleInfortuni);

        //Sesso
        List<KeySum> totaleInfortuniPerSesso = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndSesso(anno, trimestre);
        for (KeySum sesso : totaleInfortuniPerSesso) {
            String sessoDesc = sesso.getKey().equals("F") ? "femminile" : "maschile";
            logger.info("Nel {} trimestre del {}, numero totale infortuni per sesso {} : {}", trimestre, anno, sessoDesc, sesso.getTotale());
        }

        KeySum maxResultSesso = getMaxPerTotalePensioniPerTipo(totaleInfortuniPerSesso);
        String sessoDescMax = maxResultSesso.getKey().equals("F") ? "femminile" : "maschile";
        logger.info("Nel {} trimestre del {}, il sesso con piu' infortuni e' {} con {}", trimestre, anno, sessoDescMax, maxResultSesso.getTotale());

        //ClasseEta
        List<KeySum> totaleInfortuniPerClasseEta = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndClasseEta(anno, trimestre);
        for (KeySum classeEta : totaleInfortuniPerClasseEta) {
            String classeEtaDesc = ClasseEta.getDescrizioneFromCodice((Integer) classeEta.getKey());
            logger.info("Nel {} trimestre del {}, numero totale infortuni per classeEta {} : {}", trimestre, anno, classeEtaDesc, classeEta.getTotale());
        }

        KeySum maxResultClasseEta = getMaxPerTotalePensioniPerTipo(totaleInfortuniPerClasseEta);
        String classeEtaDesc = ClasseEta.getDescrizioneFromCodice((Integer) maxResultClasseEta.getKey());
        logger.info("Nel {} trimestre del {}, la classeEta con piu' infortuni e' {} con {}", trimestre, anno, classeEtaDesc, maxResultClasseEta.getTotale());

        //categoriaInfortunio
        List<KeySum> totaleInfortuniPerCategoriaInfortunio = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndCategoriaInfortunio(anno, trimestre);
        for (KeySum categoriaInfortunio : totaleInfortuniPerCategoriaInfortunio) {
            String categoriaInfortunioDesc = CategoriaInfortunio.getDescrizioneFromCodice((String) categoriaInfortunio.getKey());
            logger.info("Nel {} trimestre del {}, numero totale infortuni per categoriaInfortunio {} : {}", trimestre, anno, categoriaInfortunioDesc, categoriaInfortunio.getTotale());
        }

        KeySum maxResultCategoriaInfortuni = getMaxPerTotalePensioniPerTipo(totaleInfortuniPerCategoriaInfortunio);
        String categoriaInfortunioDescMax = CategoriaInfortunio.getDescrizioneFromCodice((String) maxResultCategoriaInfortuni.getKey());
        logger.info("Nel {} trimestre del {}, la categoriaInfortunio con piu' infortuni e' {} con {}", trimestre, anno, categoriaInfortunioDescMax, maxResultCategoriaInfortuni.getTotale());

        //Regione
        List<KeySum> totaleInfortuniPerRegione = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndRegione(anno, trimestre);
        for (KeySum regione : totaleInfortuniPerRegione) {
            String regioneDesc = Regioni.getDescrizioneFromCodice((String) regione.getKey());
            logger.info("Nel {} trimestre del {}, numero totale infortuni per regione {} : {}", trimestre, anno, regioneDesc, regione.getTotale());
        }

        KeySum maxResultRegione = getMaxPerTotalePensioniPerTipo(totaleInfortuniPerRegione);
        String regioneDescMax = Regioni.getDescrizioneFromCodice((String) maxResultRegione.getKey());
        logger.info("Nel {} trimestre del {}, il sesso con piu' inforfortuni e' {} con {}", trimestre, anno, regioneDescMax, maxResultRegione.getTotale());

    }

    private KeySum getMaxPerTotalePensioniPerTipo(List<KeySum> totalePensioniPerTipo) {
        return totalePensioniPerTipo.stream()
                .max(Comparator.comparingLong(KeySum::getTotale)) // max per totale
                .orElse(null);
    }

    void getAnalisiIncrociataDatasetInpsInail(int anno, String trimestre, List<String> regioniList) {

    }
}