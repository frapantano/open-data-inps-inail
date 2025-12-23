package it.university.opendata.integration_backend.service;

import it.university.opendata.integration_backend.dto.inail.InailDistribuzioneCategoriaDTO;
import it.university.opendata.integration_backend.dto.DistribuzioneDTO;
import it.university.opendata.integration_backend.dto.TotaliDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface InailInfortuniService {

    TotaliDTO getTotali(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerSesso(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerClasseEta(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerCategoria(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerRegione(Integer anno, String trimestre);

    InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerSesso(@RequestParam Integer anno, @RequestParam String trimestre);

    InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerClasseEta(@RequestParam Integer anno, @RequestParam String trimestre);

    InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerRegione(@RequestParam Integer anno, @RequestParam String trimestre);

}
