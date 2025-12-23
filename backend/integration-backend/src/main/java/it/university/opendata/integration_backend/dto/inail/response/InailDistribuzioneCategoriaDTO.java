package it.university.opendata.integration_backend.dto.inail.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class InailDistribuzioneCategoriaDTO {
    private Integer anno;
    private String trimestre;

    private List<InailDistribuzioneCategoriaInfortuniDTO> distribuzioni;  // una per categoria
}