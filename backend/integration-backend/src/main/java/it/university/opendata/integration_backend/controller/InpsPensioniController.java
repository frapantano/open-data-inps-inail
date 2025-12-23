package it.university.opendata.integration_backend.controller;

import it.university.opendata.integration_backend.dto.composizioni.DistribuzioneDTO;
import it.university.opendata.integration_backend.dto.composizioni.TotaliDTO;
import it.university.opendata.integration_backend.dto.inps.response.InpsDistribuzioneCategoriaDTO;
import it.university.opendata.integration_backend.service.InpsPensioniService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inps/pensioni")
public class InpsPensioniController {

    private final InpsPensioniService inpsPensioniService;

    public InpsPensioniController(InpsPensioniService inpsPensioniService) {
        this.inpsPensioniService = inpsPensioniService;
    }

    //1. Qual è il totale delle pensioni erogate nell’anno e trimestre selezionati?
    @GetMapping("/totali")
    public TotaliDTO getAllPensioni(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getTotali(anno, trimestre);
    }

    //2.1 Come si distribuiscono le pensioni per sesso nell’anno e trimestre selezionati?
    @GetMapping("/per-sesso")
    public DistribuzioneDTO perSesso(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzionePerSesso(anno, trimestre);
    }

    //2.2 Come si distribuiscono le pensioni per classe di età nell’anno e trimestre selezionati?
    @GetMapping("/per-classe-eta")
    public DistribuzioneDTO perClasseEta(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzionePerClasseEta(anno, trimestre);
    }

    //2.3 Come si distribuiscono le pensioni per categoria nell’anno e trimestre selezionati?
    @GetMapping("/per-categoria")
    public DistribuzioneDTO perCategoria(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzionePerCategoria(anno, trimestre);
    }

    //2.4 Come si distribuiscono le pensioni per regione nell’anno e trimestre selezionati?
    @GetMapping("/per-regione")
    public DistribuzioneDTO perRegione(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzionePerRegione(anno, trimestre);
    }

    //3.1 Per ogni categoria, qual è la distribuzione delle pensioni per sesso nell’anno e trimestre selezionati?
    @GetMapping("per-categoria/per-sesso")
    public InpsDistribuzioneCategoriaDTO categoriaInfortuniPerSesso(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzioneCategoriaPensioniPerSesso(anno, trimestre);
    }

    //3.2 Per ogni categoria, qual è la distribuzione delle pensioni per classe di età nell’anno e trimestre selezionati?
    @GetMapping("per-categoria/per-classe-eta")
    public InpsDistribuzioneCategoriaDTO categoriaInfortuniPerClasseEta(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzioneCategoriaPensioniPerClasseEta(anno, trimestre);
    }

    //3.3 Per ogni categoria, qual è la distribuzione delle pensioni per regione nell’anno e trimestre selezionati?
    @GetMapping("per-categoria/per-regione")
    public InpsDistribuzioneCategoriaDTO categoriaInfortuniPerRegione(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getDistribuzioneCategoriaPensioniPerRegione(anno, trimestre);
    }
}
