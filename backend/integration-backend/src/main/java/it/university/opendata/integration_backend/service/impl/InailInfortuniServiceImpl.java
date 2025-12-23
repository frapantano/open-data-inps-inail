package it.university.opendata.integration_backend.service.impl;

import it.university.opendata.integration_backend.dto.inail.InailDistribuzioneCategoriaDTO;
import it.university.opendata.integration_backend.dto.inail.InailDistribuzioneCategoriaInfortuniDTO;
import it.university.opendata.integration_backend.dto.DistribuzioneDTO;
import it.university.opendata.integration_backend.dto.TotaliDTO;
import it.university.opendata.integration_backend.repositories.inail.InfortuniInailRepository;
import it.university.opendata.integration_backend.service.InailInfortuniService;
import it.university.opendata.integration_backend.util.UtilMapping;
import it.university.opendata.integration_backend.util.proiezioni.Key2Sum;
import it.university.opendata.integration_backend.util.proiezioni.KeySum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class InailInfortuniServiceImpl implements InailInfortuniService {

    private final InfortuniInailRepository repository;

    public InailInfortuniServiceImpl(InfortuniInailRepository repository) {
        this.repository = repository;
    }

    @Override
    public TotaliDTO getTotali(Integer anno, String trimestre) {
        long totale = repository.sumNumInfortuniByAnnoAndTrimestre(anno, trimestre);

        TotaliDTO dto = new TotaliDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setTotale(totale);

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerSesso(Integer anno, String trimestre) {
        List<KeySum> totaliPerSesso = repository.sumNumInfortuniByAnnoAndTrimestreAndSesso(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("SESSO");
        dto.setValues(UtilMapping.toMap(totaliPerSesso));

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerClasseEta(Integer anno, String trimestre) {
        List<KeySum> totaliPerClasseEta = repository.sumNumInfortuniByAnnoAndTrimestreAndClasseEta(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("CLASSE_ETA");
        dto.setValues(UtilMapping.toMap(totaliPerClasseEta));

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerCategoria(Integer anno, String trimestre) {
        List<KeySum> totaliPerCategoria = repository.sumNumInfortuniByAnnoAndTrimestreAndCategoriaInfortunio(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("CATEGORIA_INFORTUNIO");
        dto.setValues(UtilMapping.toMap(totaliPerCategoria));

        return dto;
    }

    @Override
    public DistribuzioneDTO getDistribuzionePerRegione(Integer anno, String trimestre) {
        List<KeySum> totaliPerRegione = repository.sumNumInfortuniByAnnoAndTrimestreAndRegione(anno, trimestre);

        DistribuzioneDTO dto = new DistribuzioneDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("REGIONE");
        dto.setValues(UtilMapping.toMap(totaliPerRegione));

        return dto;
    }

    @Override
    public InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerSesso(Integer anno, String trimestre) {
        List<Key2Sum> totaliCategoriePerSesso = repository.sumByCategoriaAndSesso(anno, trimestre);

        Map<String, Map<String, Long>> tmp = new LinkedHashMap<>();

        for (Key2Sum r : totaliCategoriePerSesso) {
            String categoria = r.getKey1() == null ? "ND" : r.getKey1().toString();
            String sesso = r.getKey2() == null ? "ND" : r.getKey2().toString();
            Long totale = r.getTotale() == null ? 0L : r.getTotale();

            tmp.computeIfAbsent(categoria, k -> new LinkedHashMap<>())
                    .put(sesso, totale);
        }

        List<InailDistribuzioneCategoriaInfortuniDTO> distribuzioni = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> e : tmp.entrySet()) {
            InailDistribuzioneCategoriaInfortuniDTO riga = new InailDistribuzioneCategoriaInfortuniDTO();
            riga.setCategoriaInfortunio(e.getKey());
            riga.setGroupBy("SESSO");
            riga.setValues(e.getValue());
            distribuzioni.add(riga);
        }

        InailDistribuzioneCategoriaDTO out = new InailDistribuzioneCategoriaDTO();
        out.setAnno(anno);
        out.setTrimestre(trimestre);
        out.setDistribuzioni(distribuzioni);

        return out;
    }

    @Override
    public InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerClasseEta(Integer anno, String trimestre) {
        List<Key2Sum> totaliCategoriePerClasseEta = repository.sumByCategoriaAndClasseEta(anno, trimestre);

        Map<String, Map<String, Long>> tmp = new LinkedHashMap<>();

        for (Key2Sum r : totaliCategoriePerClasseEta) {
            String categoria = r.getKey1() == null ? "ND" : r.getKey1().toString();
            String sesso = r.getKey2() == null ? "ND" : r.getKey2().toString();
            Long totale = r.getTotale() == null ? 0L : r.getTotale();

            tmp.computeIfAbsent(categoria, k -> new LinkedHashMap<>())
                    .put(sesso, totale);
        }

        List<InailDistribuzioneCategoriaInfortuniDTO> distribuzioni = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> e : tmp.entrySet()) {
            InailDistribuzioneCategoriaInfortuniDTO riga = new InailDistribuzioneCategoriaInfortuniDTO();
            riga.setCategoriaInfortunio(e.getKey());
            riga.setGroupBy("CLASSE_ETA");
            riga.setValues(e.getValue());
            distribuzioni.add(riga);
        }

        InailDistribuzioneCategoriaDTO out = new InailDistribuzioneCategoriaDTO();
        out.setAnno(anno);
        out.setTrimestre(trimestre);
        out.setDistribuzioni(distribuzioni);

        return out;
    }

    @Override
    public InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerRegione(Integer anno, String trimestre) {
        List<Key2Sum> totaliCategoriePerRegione = repository.sumByCategoriaAndRegione(anno, trimestre);

        Map<String, Map<String, Long>> tmp = new LinkedHashMap<>();

        for (Key2Sum r : totaliCategoriePerRegione) {
            String categoria = r.getKey1() == null ? "ND" : r.getKey1().toString();
            String sesso = r.getKey2() == null ? "ND" : r.getKey2().toString();
            Long totale = r.getTotale() == null ? 0L : r.getTotale();

            tmp.computeIfAbsent(categoria, k -> new LinkedHashMap<>())
                    .put(sesso, totale);
        }

        List<InailDistribuzioneCategoriaInfortuniDTO> distribuzioni = new ArrayList<>();
        for (Map.Entry<String, Map<String, Long>> e : tmp.entrySet()) {
            InailDistribuzioneCategoriaInfortuniDTO riga = new InailDistribuzioneCategoriaInfortuniDTO();
            riga.setCategoriaInfortunio(e.getKey());
            riga.setGroupBy("REGIONE");
            riga.setValues(e.getValue());
            distribuzioni.add(riga);
        }

        InailDistribuzioneCategoriaDTO out = new InailDistribuzioneCategoriaDTO();
        out.setAnno(anno);
        out.setTrimestre(trimestre);
        out.setDistribuzioni(distribuzioni);

        return out;
    }
}