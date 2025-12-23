package it.university.opendata.integration_backend.dto.composizioni;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CrossInfortuniMedioGraviPensioneInvaliditaDTO {
    private Integer anno;
    private String trimestre;

    private String groupBy;                 // REGIONE / SESSO / CLASSE_ETA
    private String groupByValue;            // valore del filtro

    private Long infortuniMedioGravi;       // totale MT + GP
    private Long pensioniInvalidita;        // totale INVALIDITA
}
