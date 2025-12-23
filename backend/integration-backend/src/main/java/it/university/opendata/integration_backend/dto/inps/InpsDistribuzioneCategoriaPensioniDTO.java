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
public class InpsDistribuzioneCategoriaPensioniDTO {
    // KEY1 fisso nelle analisi 3.x
    private String categoriaPensioni;

    // dimensione della mappa (KEY2): SESSO / CLASSE_ETA / REGIONE
    private String groupBy;

    // KEY2 -> totale (SUM NUM_PENSIONI)
    private Map<String, Long> values;
}