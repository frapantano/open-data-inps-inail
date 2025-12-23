package it.university.opendata.integration_backend.service;

import it.university.opendata.integration_backend.dto.composizioni.DistribuzioneDTO;
import it.university.opendata.integration_backend.dto.composizioni.TotaliDTO;
import it.university.opendata.integration_backend.dto.inps.response.InpsDistribuzioneCategoriaDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface InpsPensioniService {

    TotaliDTO getTotali(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerSesso(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerClasseEta(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerCategoria(Integer anno, String trimestre);

    DistribuzioneDTO getDistribuzionePerRegione(Integer anno, String trimestre);

    InpsDistribuzioneCategoriaDTO getDistribuzioneCategoriaPensioniPerSesso(@RequestParam Integer anno, @RequestParam String trimestre);

    InpsDistribuzioneCategoriaDTO getDistribuzioneCategoriaPensioniPerClasseEta(@RequestParam Integer anno, @RequestParam String trimestre);

    InpsDistribuzioneCategoriaDTO getDistribuzioneCategoriaPensioniPerRegione(@RequestParam Integer anno, @RequestParam String trimestre);
}