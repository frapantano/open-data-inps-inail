package it.university.opendata.integration_backend.service;

import it.university.opendata.integration_backend.dto.inail.InailDistribuzioneCategoriaDTO;
import it.university.opendata.integration_backend.dto.inail.InailDistribuzioneInfortuniDTO;
import it.university.opendata.integration_backend.dto.inail.InailTotaliInfortuniDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface InailInfortuniService {

    InailTotaliInfortuniDTO getTotali(Integer anno, String trimestre);

    InailDistribuzioneInfortuniDTO getDistribuzionePerSesso(Integer anno, String trimestre);

    InailDistribuzioneInfortuniDTO getDistribuzionePerClasseEta(Integer anno, String trimestre);

    InailDistribuzioneInfortuniDTO getDistribuzionePerCategoria(Integer anno, String trimestre);

    InailDistribuzioneInfortuniDTO getDistribuzionePerRegione(Integer anno, String trimestre);

    InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerSesso(@RequestParam Integer anno, @RequestParam String trimestre);

    InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerClasseEta(@RequestParam Integer anno, @RequestParam String trimestre);

    InailDistribuzioneCategoriaDTO getDistribuzioneCategoriaInfortuniPerRegione(@RequestParam Integer anno, @RequestParam String trimestre);

}
