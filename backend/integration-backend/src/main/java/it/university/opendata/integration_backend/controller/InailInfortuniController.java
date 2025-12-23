package it.university.opendata.integration_backend.controller;

import it.university.opendata.integration_backend.dto.inail.InailDistribuzioneCategoriaDTO;
import it.university.opendata.integration_backend.dto.DistribuzioneDTO;
import it.university.opendata.integration_backend.dto.TotaliDTO;
import it.university.opendata.integration_backend.service.InailInfortuniService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inail/infortuni")
public class InailInfortuniController {

    private final InailInfortuniService inailInfortuniService;

    public InailInfortuniController(InailInfortuniService inailInfortuniService) {
        this.inailInfortuniService = inailInfortuniService;
    }

    //1. Entità del fenomeno: Qual è il numero complessivo di infortuni registrati nel periodo analizzato?
    @GetMapping("/totali")
    public TotaliDTO getAllInfortuni(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getTotali(anno, trimestre);
    }

    //2.1 Quale sesso presenta il maggior numero di infortuni nel periodo considerato e come si distribuiscono gli infortuni tra i sessi?
    @GetMapping("/per-sesso")
    public DistribuzioneDTO perSesso(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzionePerSesso(anno, trimestre);
    }

    //2.2 Quali classi di età risultano più rappresentate in termini di numero di infortuni nel periodo considerato?
    @GetMapping("/per-classe-eta")
    public DistribuzioneDTO perClasseEta(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzionePerClasseEta(anno, trimestre);
    }

    //2.3 Quali categorie di infortunio risultano più frequenti nel periodo analizzato (ranking per numerosità)?
    @GetMapping("/per-categoria")
    public DistribuzioneDTO perCategoria(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzionePerCategoria(anno, trimestre);
    }

    //2.4 Quali regioni presentano il maggior numero di infortuni nel periodo analizzato e come si distribuiscono gli infortuni sul territorio?
    @GetMapping("/per-regione")
    public DistribuzioneDTO perRegione(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzionePerRegione(anno, trimestre);
    }


    //3.1 All’interno di ciascuna tipologia di infortunio, come si distribuiscono gli infortuni tra uomini e donne?
    @GetMapping("per-categoria/per-sesso")
    public InailDistribuzioneCategoriaDTO categoriaInfortuniPerSesso(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzioneCategoriaInfortuniPerSesso(anno, trimestre);
    }

    //3.2 All’interno di ciascuna tipologia di infortunio, come si distribuiscono gli infortuni tra le diverse classi di età?
    @GetMapping("per-categoria/per-classe-eta")
    public InailDistribuzioneCategoriaDTO categoriaInfortuniPerClasseEta(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzioneCategoriaInfortuniPerClasseEta(anno, trimestre);
    }

    //3.3 All’interno di ciascuna tipologia di infortunio, come si distribuiscono gli infortuni a livello territoriale (per regione)?
    @GetMapping("per-categoria/per-regione")
    public InailDistribuzioneCategoriaDTO categoriaInfortuniPerRegione(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getDistribuzioneCategoriaInfortuniPerRegione(anno, trimestre);
    }
}
