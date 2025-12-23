package it.university.opendata.integration_backend.dto.composizioni;

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
public class AnalisiIncrociataDTO {

    private Integer anno;
    private String trimestre;

    private String groupBy;         //SESSO / CLASSE_ETA / REGIONE
    private String groupByValue;

    private Map<String, Long> inail;
    private Map<String, Long> inps;
}
