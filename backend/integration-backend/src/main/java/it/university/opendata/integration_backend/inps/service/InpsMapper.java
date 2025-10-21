package it.university.opendata.integration_backend.inps.service;

import it.university.opendata.integration_backend.inps.dto.InpsJsonRecord;
import it.university.opendata.integration_backend.inps.dto.InpsKey;
import it.university.opendata.integration_backend.inps.entity.PensioniInps;
import it.university.opendata.integration_backend.util.RegioniSingleton;
import it.university.opendata.integration_backend.util.InpsMapping;
import org.springframework.stereotype.Component;

@Component
public class InpsMapper {

    public InpsKey inpsKeyOf(InpsJsonRecord r) {
        return new InpsKey(
                r.getAnno(),
                InpsMapping.trimestreToRoman(r.getTrimestre()),
                RegioniSingleton.codiceFromNomeRegione(r.getRegione()),
                InpsMapping.codiceInpsFromDescSesso(r.getSesso()),
                InpsMapping.codiceInpsFromDescClasseEta(r.getClasseEta()),
                InpsMapping.codiceInpsFromDescPensione(r.getCategoria())
        );
    }

    public PensioniInps toInpsEntity(InpsKey key, Double somma) {
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
}
