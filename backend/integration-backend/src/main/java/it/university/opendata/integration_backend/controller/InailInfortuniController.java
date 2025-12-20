package it.university.opendata.integration_backend.controller;

import it.university.opendata.integration_backend.dto.inail.InailInfortuniTotaliDTO;
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

    @GetMapping("/totali")
    public InailInfortuniTotaliDTO getAllInfortuni(@RequestParam Integer anno, @RequestParam String trimestre) {
        return inailInfortuniService.getTotali(anno, trimestre);
    }
}
