package it.university.opendata.integration_backend.service.impl;

import it.university.opendata.integration_backend.dto.composizioni.DistribuzioneDTO;
import it.university.opendata.integration_backend.dto.composizioni.TotaliDTO;
import it.university.opendata.integration_backend.dto.inps.response.InpsDistribuzioneCategoriaDTO;
import it.university.opendata.integration_backend.dto.inps.response.InpsDistribuzioneCategoriaPensioniDTO;
import it.university.opendata.integration_backend.repositories.PensioniInpsRepository;
import it.university.opendata.integration_backend.service.InpsPensioniService;
import it.university.opendata.integration_backend.util.UtilMapping;
import it.university.opendata.integration_backend.util.proiezioni.Key2Sum;
import it.university.opendata.integration_backend.util.proiezioni.KeySum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class InpsPensioniServiceImpl  implements InpsPensioniService {

    private final PensioniInpsRepository repository;

    public InpsPensioniServiceImpl(PensioniInpsRepository repository) {
        this.repository = repository;
    }

    @Override
    public TotaliDTO getTotali(Integer anno, String trimestre) {
        long totale = repository.sumNumPensioniByAnnoAndTrimestre(anno, trimestre);

        TotaliDTO dto = new TotaliDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setTotale(totale);

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerSesso(Integer anno, String trimestre) {
        List<KeySum> totaliPerSesso = repository.sumNumPensioniByAnnoAndTrimestreAndSesso(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("SESSO");
        dto.setValues(UtilMapping.toMap(totaliPerSesso));

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerClasseEta(Integer anno, String trimestre) {
        List<KeySum> totaliPerClasseEta = repository.sumNumPensioniByAnnoAndTrimestreAndClasseEta(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("CLASSE_ETA");
        dto.setValues(UtilMapping.toMap(totaliPerClasseEta));

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerCategoria(Integer anno, String trimestre) {
        List<KeySum> totaliPerCategoria = repository.sumNumPensioniByAnnoAndTrimestreAndCategoriaPensione(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("CATEGORIA_PENSIONE");
        dto.setValues(UtilMapping.toMap(totaliPerCategoria));

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerRegione(Integer anno, String trimestre) {
        List<KeySum> totaliPerRegione = repository.sumNumPensioniByAnnoAndTrimestreAndRegione(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("REGIONE");
        dto.setValues(UtilMapping.toMap(totaliPerRegione));

        return dto;
    }

    @Override
    public InpsDistribuzioneCategoriaDTO getDistribuzioneCategoriaPensioniPerSesso(Integer anno, String trimestre) {
        List<Key2Sum> totaliCategoriePerSesso = repository.sumByCategoriaAndSesso(anno, trimestre);

        Map<String, Map<String, Long>> tmp = new LinkedHashMap<>();

        for (Key2Sum r : totaliCategoriePerSesso) {
            String categoria = r.getKey1() == null ? "ND" : r.getKey1().toString();
            String sesso = r.getKey2() == null ? "ND" : r.getKey2().toString();
            Long totale = r.getTotale() == null ? 0L : r.getTotale();

            tmp.computeIfAbsent(categoria, k -> new LinkedHashMap<>())
                    .put(sesso, totale);
        }

        List<InpsDistribuzioneCategoriaPensioniDTO> distribuzioni = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> e : tmp.entrySet()) {
            InpsDistribuzioneCategoriaPensioniDTO riga = new InpsDistribuzioneCategoriaPensioniDTO();
            riga.setCategoriaPensioni(e.getKey());
            riga.setGroupBy("SESSO");
            riga.setValues(e.getValue());
            distribuzioni.add(riga);
        }

        InpsDistribuzioneCategoriaDTO out = new InpsDistribuzioneCategoriaDTO();
        out.setAnno(anno);
        out.setTrimestre(trimestre);
        out.setDistribuzioni(distribuzioni);

        return out;
    }

    @Override
    public InpsDistribuzioneCategoriaDTO getDistribuzioneCategoriaPensioniPerClasseEta(Integer anno, String trimestre) {
        List<Key2Sum> totaliCategoriePerClasseEta = repository.sumByCategoriaAndClasseEta(anno, trimestre);

        Map<String, Map<String, Long>> tmp = new LinkedHashMap<>();

        for (Key2Sum r : totaliCategoriePerClasseEta) {
            String categoria = r.getKey1() == null ? "ND" : r.getKey1().toString();
            String sesso = r.getKey2() == null ? "ND" : r.getKey2().toString();
            Long totale = r.getTotale() == null ? 0L : r.getTotale();

            tmp.computeIfAbsent(categoria, k -> new LinkedHashMap<>())
                    .put(sesso, totale);
        }

        List<InpsDistribuzioneCategoriaPensioniDTO> distribuzioni = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> e : tmp.entrySet()) {
            InpsDistribuzioneCategoriaPensioniDTO riga = new InpsDistribuzioneCategoriaPensioniDTO();
            riga.setCategoriaPensioni(e.getKey());
            riga.setGroupBy("CLASSE_ETA");
            riga.setValues(e.getValue());
            distribuzioni.add(riga);
        }

        InpsDistribuzioneCategoriaDTO out = new InpsDistribuzioneCategoriaDTO();
        out.setAnno(anno);
        out.setTrimestre(trimestre);
        out.setDistribuzioni(distribuzioni);

        return out;
    }

    @Override
    public InpsDistribuzioneCategoriaDTO getDistribuzioneCategoriaPensioniPerRegione(Integer anno, String trimestre) {
        List<Key2Sum> totaliCategoriePerRegione = repository.sumByCategoriaAndRegione(anno, trimestre);

        Map<String, Map<String, Long>> tmp = new LinkedHashMap<>();

        for (Key2Sum r : totaliCategoriePerRegione) {
            String categoria = r.getKey1() == null ? "ND" : r.getKey1().toString();
            String sesso = r.getKey2() == null ? "ND" : r.getKey2().toString();
            Long totale = r.getTotale() == null ? 0L : r.getTotale();

            tmp.computeIfAbsent(categoria, k -> new LinkedHashMap<>())
                    .put(sesso, totale);
        }

        List<InpsDistribuzioneCategoriaPensioniDTO> distribuzioni = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> e : tmp.entrySet()) {
            InpsDistribuzioneCategoriaPensioniDTO riga = new InpsDistribuzioneCategoriaPensioniDTO();
            riga.setCategoriaPensioni(e.getKey());
            riga.setGroupBy("REGIONE");
            riga.setValues(e.getValue());
            distribuzioni.add(riga);
        }

        InpsDistribuzioneCategoriaDTO out = new InpsDistribuzioneCategoriaDTO();
        out.setAnno(anno);
        out.setTrimestre(trimestre);
        out.setDistribuzioni(distribuzioni);

        return out;
    }
}
