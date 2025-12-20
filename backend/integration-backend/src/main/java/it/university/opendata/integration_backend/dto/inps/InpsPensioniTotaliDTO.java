package it.university.opendata.integration_backend.dto.inps;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class InpsPensioniTotaliDTO {

    private Integer anno;
    private String trimestre;
    private long totale;

    private Map<String, Long> perSesso;
    private Map<String, Long> perClasseEta;
    private Map<String, Long> perCategoria;
    private Map<String, Long> perRegione;
}
