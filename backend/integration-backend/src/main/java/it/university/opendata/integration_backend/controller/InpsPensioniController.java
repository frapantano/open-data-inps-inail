package it.university.opendata.integration_backend.controller;

import it.university.opendata.integration_backend.dto.inps.InpsPensioniTotaliDTO;
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

    @GetMapping("/totali")
    public InpsPensioniTotaliDTO getAllPensioni(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inpsPensioniService.getTotali(anno, trimestre);
    }
}
