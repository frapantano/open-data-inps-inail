package it.university.opendata.integration_backend.service.impl;

import it.university.opendata.integration_backend.dto.inps.InpsPensioniTotaliDTO;
import it.university.opendata.integration_backend.repositories.inps.PensioniInpsRepository;
import it.university.opendata.integration_backend.service.InpsPensioniService;
import it.university.opendata.integration_backend.util.UtilMapping;
import org.springframework.stereotype.Service;

@Service
public class InpsPensioniServiceImpl  implements InpsPensioniService {

    private final PensioniInpsRepository repository;

    public InpsPensioniServiceImpl(PensioniInpsRepository repository) {
        this.repository = repository;
    }

    @Override
    public InpsPensioniTotaliDTO getTotali(Integer anno, String trimestre) {
        long totale = repository.sumNumPensioniByAnnoAndTrimestre(anno, trimestre);

        InpsPensioniTotaliDTO dto = new InpsPensioniTotaliDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setTotale(totale);

        dto.setPerSesso(UtilMapping.toMap(repository.sumNumPensioniByAnnoAndTrimestreAndSesso(anno, trimestre)));
        dto.setPerClasseEta(UtilMapping.toMap(repository.sumNumPensioniByAnnoAndTrimestreAndClasseEta(anno, trimestre)));
        dto.setPerCategoria(UtilMapping.toMap(repository.sumNumPensioniByAnnoAndTrimestreAndCategoriaPensione(anno, trimestre)));
        dto.setPerRegione(UtilMapping.toMap(repository.sumNumPensioniByAnnoAndTrimestreAndRegione(anno, trimestre)));

        return dto;
    }
}
