package it.university.opendata.integration_backend.controller;

import it.university.opendata.integration_backend.dto.composizioni.AnalisiIncrociataDTO;
import it.university.opendata.integration_backend.dto.composizioni.CrossInfortuniMedioGraviPensioneInvaliditaDTO;
import it.university.opendata.integration_backend.dto.composizioni.CrossInfortuniMortaliPensioneSuperstitiDTO;
import it.university.opendata.integration_backend.service.AnalisiIncrociataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analisi-incrociata/composizione")
public class AnalisiIncrociataController {

    private final AnalisiIncrociataService analisiIncrociataService;

    public AnalisiIncrociataController(AnalisiIncrociataService analisiIncrociataService) {
        this.analisiIncrociataService = analisiIncrociataService;
    }

    //1.1 Confronto delle composizioni per sesso: All’interno di uno stesso sottogruppo demografico definito dal sesso, come si distribuiscono le categorie di infortunio INAIL e le categorie di pensione INPS?
    @GetMapping("/sesso")
    public AnalisiIncrociataDTO getComposizioniSesso(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam String codSesso) {
        return analisiIncrociataService.getComposizioniSesso(anno, trimestre, codSesso);
    }

    //1.2 Confronto delle composizioni per classe di età: All’interno di uno stesso sottogruppo demografico definito dalla classe di età, come si distribuiscono le categorie di infortunio INAIL e le categorie di pensione INPS?
    @GetMapping("/classe-eta")
    public AnalisiIncrociataDTO getComposizioniClasseEta(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam Integer codClasseEta) {
        return analisiIncrociataService.getComposizioniClasseEta(anno, trimestre, codClasseEta);
    }

    //1.3 Confronto delle composizioni per regione: All’interno di una stessa regione, come differiscono le composizioni delle categorie di infortunio INAIL e delle categorie di pensione INPS?
    @GetMapping("/regione")
    public AnalisiIncrociataDTO getComposizioniRegione(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam String codIstatRegione) {
        return analisiIncrociataService.getComposizioniRegione(anno, trimestre, codIstatRegione);
    }

    //2 Confronti tematici tra categorie INAIL e INPS

    //2.1 Infortuni mortali ↔ Pensioni ai superstiti
    //2.1.1 Confronto per contesto territoriale e temporale: Nel medesimo contesto di genere e temporale, come si confrontano i volumi aggregati degli infortuni con esito mortale (MO) e delle pensioni ai superstiti?
    @GetMapping("/mo-superstiti/sesso")
    public CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoVsSuperstitiSesso(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam String codSesso) {
        return analisiIncrociataService.getInfMoVsSuperstitiSesso(anno, trimestre, codSesso);
    }

    //2.1.2 Confronto per genere e contesto temporale: Nel medesimo contesto di classe di età e temporale, come si confrontano i volumi aggregati degli infortuni con esito mortale (MO) e delle pensioni ai superstiti?
    @GetMapping("/mo-superstiti/classe-eta")
    public CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoSuperstitiClasseEta(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam Integer codClasseEta) {
        return analisiIncrociataService.getInfMoSuperstitiClasseEta(anno, trimestre, codClasseEta);
    }

    //2.1.3 Confronto per classe di età e contesto temporale: Nel medesimo contesto territoriale di età e temporale, come si confrontano i volumi aggregati degli infortuni con esito mortale (MO) e delle pensioni ai superstiti?
    @GetMapping("/mo-superstiti/regione")
    public CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoSuperstitiRegione(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam String codIstatRegione) {
        return analisiIncrociataService.getInfMoSuperstitiRegione(anno, trimestre, codIstatRegione);
    }

    //2.2 Infortuni con menomazione medio-grave o permanente ↔ Pensioni di invalidità
    //2.2.1 Confronto per contesto territoriale e temporale: Nel medesimo contesto di genere e temporale, come si confrontano i volumi aggregati degli infortuni caratterizzati da menomazioni medio-gravi o permanenti (MT + GP) e delle pensioni di invalidità?
    @GetMapping("/mt-gp-invalidita/sesso")
    public CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaSesso(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam String codSesso) {
        return analisiIncrociataService.getInfMtGpVsInvaliditaSesso(anno, trimestre, codSesso);
    }

    //2.2.2 Confronto per genere e contesto temporale: Nel medesimo contesto di classe di eta e temporale, come si confrontano i volumi aggregati degli infortuni caratterizzati da menomazioni medio-gravi o permanenti (MT + GP) e delle pensioni di invalidità?
    @GetMapping("/mt-gp-invalidita/classe-eta")
    public CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaClasseEta(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam Integer codClasseEta) {
        return analisiIncrociataService.getInfMtGpVsInvaliditaClasseEta(anno, trimestre, codClasseEta);
    }

    //2.2.3 Confronto per classe di età e contesto temporale: Nel medesimo contesto territoriale e temporale, come si confrontano i volumi aggregati degli infortuni caratterizzati da menomazioni medio-gravi o permanenti (MT + GP) e delle pensioni di invalidità?
    @GetMapping("/mt-gp-invalidita/regione")
    public CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaRegione(@RequestParam Integer anno, @RequestParam String trimestre, @RequestParam String codIstatRegione) {
        return analisiIncrociataService.getInfMtGpVsInvaliditaRegione(anno, trimestre, codIstatRegione);
    }
}
