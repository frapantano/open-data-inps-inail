package it.university.opendata.integration_backend.inail.service;

import it.university.opendata.integration_backend.inail.dto.InailKey;
import it.university.opendata.integration_backend.inail.dto.InfortunioXmlDTO;
import it.university.opendata.integration_backend.inail.entity.InfortuniInail;
import it.university.opendata.integration_backend.util.CategoriaInfortunio;
import it.university.opendata.integration_backend.util.ClasseEta;
import it.university.opendata.integration_backend.util.Regioni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class InailMapper {
    private static final Logger logger = LoggerFactory.getLogger(InailMapper.class);

    private InailKey inailKeyOf(InfortunioXmlDTO r, Integer anno, String trimestre, String regione) {
        // Mapping
        String codRegione = Regioni.getCodiceFromDescrizione(regione);
        Integer codEta = ClasseEta.codiceDaEta(r.getEta());
        String codCat = CategoriaInfortunio.codiceFromGrado(r.getGradoMenomazione(), r.getDataMorte());

        // Verifica nulls
        List<String> nulls = new ArrayList<>();
        if (anno == null) nulls.add("anno");
        if (trimestre == null) nulls.add("trimestre");
        if (regione == null) nulls.add("regione(descr.)");
        if (codRegione == null) nulls.add("codRegione");
        if (r.getGenere() == null) nulls.add("genere");
        if (codEta == null) nulls.add("codEta");
        if (codCat == null) nulls.add("codCategoria");

        if (!nulls.isEmpty()) {
            String dtoStr = String.format(
                    "{anno =%s, trimestre=%s, idCaso=%s, sesso=%s, eta=%s, regione=%s, dataMorte=%s, gradoMenom=%s}",
                    Objects.toString(anno, "null"),
                    Objects.toString(trimestre, "null"),
                    Objects.toString(r.getIdentificativoCaso(), "null"),
                    Objects.toString(r.getGenere(), "null"),
                    Objects.toString(codEta, "null"),
                    Objects.toString(regione, "null"),
                    Objects.toString(r.getDataMorte(), "null"),
                    Objects.toString(r.getGradoMenomazione(), "null")
            );

            logger.warn("Record INAIL scartato per campi null: {} | DTO={}",
                    String.join(", ", nulls), dtoStr);
            return null; // chiave scartata
        }

        return new InailKey(
                anno,
                trimestre,
                codRegione,
                r.getGenere(),
                codEta,
                codCat
        );
    }

    private InfortuniInail toInailEntity(InailKey key, Integer numInfortuni) {
        InfortuniInail e = new InfortuniInail();
        e.setAnno(key.getAnno());
        e.setTrimestre(key.getTrimestre());
        e.setRegione(key.getRegione());
        e.setSesso(key.getSesso());
        e.setClasseEta(key.getClasseEta());
        e.setCategoriaInfortunio(key.getCategoria());
        e.setNumInfortuni(numInfortuni);
        return e;
    }

    public List<InfortuniInail> getInfortuniInail(List<InfortunioXmlDTO> infortuni, Integer anno, String trimestre, String regione) {
        if (infortuni == null || infortuni.isEmpty()) {
            return Collections.emptyList();
        }

        //Aggrega dati INAIL
        // DISTINCT identificativi per chiave
        Map<InailKey, Set<String>> distinctIdsByKey = new HashMap<>();

        for (InfortunioXmlDTO r : infortuni) {
            if (r == null) continue;

            String idCaso = r.getIdentificativoCaso();
            if (idCaso == null || idCaso.isBlank()) continue;

            // inailKeyOf ritorna null se qualche campo è null e logga il record scartato
            InailKey key = inailKeyOf(r, anno, trimestre, regione);
            if (key == null) continue;

            distinctIdsByKey.computeIfAbsent(key, k -> new HashSet<>()).add(idCaso);
        }

        //Mapping risultati in InfortuniInail Entity
        List<InfortuniInail> risultato = new ArrayList<>(distinctIdsByKey.size());
        for (Map.Entry<InailKey, Set<String>> e : distinctIdsByKey.entrySet()) {
            risultato.add(toInailEntity(e.getKey(), e.getValue().size()));
        }
        return risultato;
    }
}
