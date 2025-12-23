package it.university.opendata.integration_backend.dto.inps;

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
public class InpsDistribuzioneCategoriaDTO {
    private Integer anno;
    private String trimestre;

    private List<InpsDistribuzioneCategoriaPensioniDTO> distribuzioni;  // una per categoria
}