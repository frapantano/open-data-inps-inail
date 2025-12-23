package it.university.opendata.integration_backend.mapper;

import it.university.opendata.integration_backend.dto.inps.payload.InpsJsonRecord;
import it.university.opendata.integration_backend.dto.inps.payload.InpsKey;
import it.university.opendata.integration_backend.entity.PensioniInps;
import it.university.opendata.integration_backend.util.CategoriaPensione;
import it.university.opendata.integration_backend.util.ClasseEta;
import it.university.opendata.integration_backend.util.Regioni;
import it.university.opendata.integration_backend.util.UtilMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class InpsMapper {
    private static final Logger logger = LoggerFactory.getLogger(InpsMapper.class);

    private InpsKey inpsKeyOf(InpsJsonRecord r) {
        //Mapping
        String codRegione = Regioni.getCodiceFromDescrizione(r.getRegione());
        Integer codEta = ClasseEta.codiceInpsFromDescrizione(r.getClasseEta());
        String codPensione = CategoriaPensione.getCodiceFromDescrizione(r.getCategoria());
        String trimestre = UtilMapping.trimestreToRoman(r.getTrimestre());
        String sesso = UtilMapping.codiceInpsFromDescSesso(r.getSesso());

        // Verifica nulls
        List<String> nulls = new ArrayList<>();
        if (r.getAnno() == null) nulls.add("anno");
        if (trimestre == null) nulls.add("trimestre");
        if (codRegione == null) nulls.add("codRegione");
        if (sesso == null) nulls.add("genere");
        if (codEta == null) nulls.add("codEta");
        if (codPensione == null) nulls.add("codPensione");

        if (!nulls.isEmpty()) {
            String dtoStr = String.format(
                    "{anno =%s, trimestre=%s, sesso=%s, eta=%s, regione=%s, codPensione=%s}",
                    Objects.toString(r.getAnno(), "null"),
                    Objects.toString(trimestre, "null"),
                    Objects.toString(sesso, "null"),
                    Objects.toString(codEta, "null"),
                    Objects.toString(codRegione, "null"),
                    Objects.toString(codPensione, "null")
            );

            logger.warn("Record INPS scartato per campi null: {} | DTO={}",
                    String.join(", ", nulls), dtoStr);
            return null; // chiave scartata
        }

        return new InpsKey(
                r.getAnno(),
                trimestre,
                codRegione,
                sesso,
                codEta,
                codPensione
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
        if (pensioniItalia == null || pensioniItalia.isEmpty()) {
            return Collections.emptyList();
        }

        //Aggrega dati INPS
        Map<InpsKey, Double> totaliAggregati = new HashMap<>();
        for (InpsJsonRecord r : pensioniItalia) {
            if (r == null) continue;

            InpsKey key = inpsKeyOf(r);   // può essere null (in tal caso il metodo avrà già loggato)
            if (key == null) continue;    // escludi i result set con key nulla

            Number num = r.getNumeroPensioni();
            if (num == null) continue;

            double valore = num.doubleValue();
            totaliAggregati.merge(key, valore, Double::sum);
        }

        //Mapping risultati in PensioniInps Entity
        return totaliAggregati.entrySet().stream()
                .map(e -> toInpsEntity(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
