package it.university.opendata.integration_backend.dto.inail;

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
public class InailDistribuzioneInfortuniDTO {

    private Integer anno;
    private String trimestre;

    // per capire cosa rappresenta la mappa: SESSO / CLASSE_ETA / CATEGORIA / REGIONE
    private String groupBy;

    // chiave -> totale (SUM NUM_INFORTUNI)
    private Map<String, Long> values;
}