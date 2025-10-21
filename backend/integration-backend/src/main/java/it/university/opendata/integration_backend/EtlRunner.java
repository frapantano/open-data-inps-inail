package it.university.opendata.integration_backend;

import it.university.opendata.integration_backend.inail.dto.InfortunioXmlDTO;
import it.university.opendata.integration_backend.inail.entity.InfortuniInail;
import it.university.opendata.integration_backend.inail.repo.InfortuniInailRepository;
import it.university.opendata.integration_backend.inail.service.InailMapper;
import it.university.opendata.integration_backend.inail.service.reader.InailXmlClasspathReader;
import it.university.opendata.integration_backend.inps.dto.InpsJsonRecord;
import it.university.opendata.integration_backend.inps.entity.PensioniInps;
import it.university.opendata.integration_backend.inps.repo.PensioniInpsRepository;
import it.university.opendata.integration_backend.inps.service.InpsMapper;
import it.university.opendata.integration_backend.inps.service.reader.InpsJsonClasspathReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class EtlRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EtlRunner.class);

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

    @Override
    public void run(String... args) throws IOException {
        String path = "datasets/2024/primoSemestre/";

        //Caricamento dati INPS
        List<InpsJsonRecord> pensioniItalia = inpsReader.readFromClasspath(path + "inps/pensioniItalia.json");
        if (pensioniItalia == null) {
            logger.error("ATTENZIONE: File JSON 'pensioniItalia' non trovato o vuoto");
            return;
        }

        //Aggrega e mappa i dati in PensioniInps Entity
        List<PensioniInps> entitiesPensioni = inpsMapper.getPensioniInps(pensioniItalia);

        //Salva in PENSIONI_INPS
        inpsRepository.saveAll(entitiesPensioni);
        logger.info("ETL INPS completato: " + entitiesPensioni.size() + " record caricati.");

        //Caricamento dati INAIL
        //LOMBARDIA
        List<InfortunioXmlDTO> infortuniLombardia = inailReader.readFromClasspath(path + "inail/infortuni_Lombardia_sample.xml");
        if (infortuniLombardia == null) {
            logger.error("ATTENZIONE: File XML 'infortuni_Lombardia_sample' non trovato o vuoto");
            return;
        }
        //LAZIO
        List<InfortunioXmlDTO> infortuniLazio = inailReader.readFromClasspath(path + "inail/infortuni_Lazio_sample.xml");
        if (infortuniLazio == null) {
            logger.error("ATTENZIONE: File XML 'infortuni_Lazio_sample' non trovato o vuoto");
            return;
        }
        //CALABRIA
        List<InfortunioXmlDTO> infortuniCalabria = inailReader.readFromClasspath(path + "inail/infortuni_Calabria_sample.xml");
        if (infortuniCalabria == null) {
            logger.error("ATTENZIONE: File XML 'infortuni_Calabria_sample' non trovato o vuoto");
            return;
        }

        //Salva in INFORTUNI_INAIL
        List<InfortuniInail> entitiesLombardia = inailMapper.getInfortuniInail(infortuniLombardia, 2024, "I", "Lombardia");
        inailRepository.saveAll(entitiesLombardia);
        logger.info("ETL INAIL Lombardia completato: " + entitiesLombardia.size() + " record caricati.");

        List<InfortuniInail> entitiesLazio = inailMapper.getInfortuniInail(infortuniLazio, 2024, "I", "Lazio");
        inailRepository.saveAll(entitiesLazio);
        logger.info("ETL INAIL Lazio completato: " + entitiesLazio.size() + " record caricati.");

        List<InfortuniInail> entitiesCalabria = inailMapper.getInfortuniInail(infortuniCalabria, 2024, "I", "Calabria");
        inailRepository.saveAll(entitiesCalabria);
        logger.info("ETL INAIL Calabria completato: " + entitiesCalabria.size() + " record caricati.");
    }

}
