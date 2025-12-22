package it.university.opendata.integration_backend.dto.inail;

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
public class InailTotaliInfortuniDTO {

    private Integer anno;
    private String trimestre;
    private long totale;
}
