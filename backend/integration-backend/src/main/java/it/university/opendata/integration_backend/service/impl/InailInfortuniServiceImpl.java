package it.university.opendata.integration_backend.service.impl;

import it.university.opendata.integration_backend.dto.inail.InailInfortuniTotaliDTO;
import it.university.opendata.integration_backend.repositories.inail.InfortuniInailRepository;
import it.university.opendata.integration_backend.service.InailInfortuniService;
import it.university.opendata.integration_backend.util.UtilMapping;
import org.springframework.stereotype.Service;

@Service
public class InailInfortuniServiceImpl implements InailInfortuniService {

    private final InfortuniInailRepository repository;

    public InailInfortuniServiceImpl(InfortuniInailRepository repository) {
        this.repository = repository;
    }

    @Override
    public InailInfortuniTotaliDTO getTotali(Integer anno, String trimestre) {
        long totale = repository.sumNumInfortuniByAnnoAndTrimestre(anno, trimestre);

        InailInfortuniTotaliDTO dto = new InailInfortuniTotaliDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setTotale(totale);

        dto.setPerSesso(UtilMapping.toMap(repository.sumNumInfortuniByAnnoAndTrimestreAndSesso(anno, trimestre)));
        dto.setPerClasseEta(UtilMapping.toMap(repository.sumNumInfortuniByAnnoAndTrimestreAndClasseEta(anno, trimestre)));
        dto.setPerCategoria(UtilMapping.toMap(repository.sumNumInfortuniByAnnoAndTrimestreAndCategoriaInfortunio(anno, trimestre)));
        dto.setPerRegione(UtilMapping.toMap(repository.sumNumInfortuniByAnnoAndTrimestreAndRegione(anno, trimestre)));

        return dto;
    }
}
