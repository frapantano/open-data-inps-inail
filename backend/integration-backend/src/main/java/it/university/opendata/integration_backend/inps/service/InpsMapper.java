package it.university.opendata.integration_backend.inps.service;

import it.university.opendata.integration_backend.inps.dto.InpsJsonRecord;
import it.university.opendata.integration_backend.inps.dto.InpsKey;
import it.university.opendata.integration_backend.inps.entity.PensioniInps;
import it.university.opendata.integration_backend.util.CategoriaPensione;
import it.university.opendata.integration_backend.util.ClasseEta;
import it.university.opendata.integration_backend.util.Regioni;
import it.university.opendata.integration_backend.util.UtilMapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InpsMapper {

    private InpsKey inpsKeyOf(InpsJsonRecord r) {
        return new InpsKey(
                r.getAnno(),
                UtilMapping.trimestreToRoman(r.getTrimestre()),
                Regioni.getCodiceFromDescrizione(r.getRegione()),
                UtilMapping.codiceInpsFromDescSesso(r.getSesso()),
                ClasseEta.codiceInpsFromDescrizione(r.getClasseEta()),
                CategoriaPensione.getCodiceFromDescrizione(r.getCategoria())
        );
    }

    private PensioniInps toInpsEntity(InpsKey key, Double somma) {
        PensioniInps e = new PensioniInps();
        e.setAnno(key.getAnno());
        e.setTrimestre(key.getTrimestre());
        e.setRegione(key.getRegione());
        e.setSesso(key.getSesso());
        e.setClasseEta(key.getClasseEta());
        e.setCategoriaPensione(key.getCategoria());
        e.setNumPensioni(somma.intValue());
        return e;
    }

    public List<PensioniInps> getPensioniInps(List<InpsJsonRecord> pensioniItalia) {
        //Aggrega dati Inps
        Map<InpsKey, Double> totaliAggregati = pensioniItalia.stream()
                .collect(Collectors.groupingBy(
                        this :: inpsKeyOf,
                        Collectors.summingDouble(InpsJsonRecord::getNumeroPensioni)
                ));

        //Mapping risultati in PensioniInps Entity
        return totaliAggregati.entrySet().stream()
                .map(e -> toInpsEntity(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
