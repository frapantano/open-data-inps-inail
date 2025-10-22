package it.university.opendata.integration_backend.inail.service;

import it.university.opendata.integration_backend.inail.dto.InailKey;
import it.university.opendata.integration_backend.inail.dto.InfortunioXmlDTO;
import it.university.opendata.integration_backend.inail.entity.InfortuniInail;
import it.university.opendata.integration_backend.util.CategoriaInfortunio;
import it.university.opendata.integration_backend.util.ClasseEta;
import it.university.opendata.integration_backend.util.Regioni;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InailMapper {

    private InailKey inailKeyOf(InfortunioXmlDTO r, int anno, String trimestre, String regione) {
        return new InailKey(
                anno,
                trimestre,
                Regioni.getCodiceFromDescrizione(regione),
                r.getGenere(),
                ClasseEta.codiceDaEta(r.getEta()),
                CategoriaInfortunio.codiceFromGrado(r.getGradoMenomazione(), r.getDataMorte())
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

    public List<InfortuniInail> getInfortuniInail(List<InfortunioXmlDTO> infortuni, int anno, String trimestre, String regione) {

        //Aggrega dati INAIL
        Map<InailKey, Integer> totaliAggregati = infortuni.stream()
                .filter(r -> r.getIdentificativoCaso() != null && !r.getIdentificativoCaso().isBlank())
                .collect(Collectors.groupingBy(
                        r -> inailKeyOf(r, anno, trimestre, regione),
                        Collectors.collectingAndThen(
                                Collectors.mapping(
                                        InfortunioXmlDTO::getIdentificativoCaso,
                                        Collectors.toSet()  // DISTINCT
                                ),
                                set -> set.size()
                        )
                ));

        //Mapping risultati in InfortuniInail Entity
        return  totaliAggregati.entrySet().stream()
                .map(e -> toInailEntity(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
