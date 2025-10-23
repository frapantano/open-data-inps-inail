package it.university.opendata.integration_backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public enum CategoriaInfortunio {

    MORTALE("MO", null, null, "Mortale"),
    FRANCHIGIA("FR", -1, 5, "Franchigia"),             // [-1, 5)
    LIQUIDAZIONE("LQ", 5, 16, "Liquidazione"),           // [5, 16)
    MEDIO_TEMPORANEO("MT", 16, 80, "Medio Temporaneo"),      // [16, 80)
    GRAVE_PERMANENTE("GP", 80, null, "Grave Permanente");    // [80, +∞)

    private static final Logger logger = LoggerFactory.getLogger(CategoriaInfortunio.class);

    private final String codice;
    private final Integer minIncluso;
    private final Integer maxEscluso;    // null = +∞
    private final String descrizione;

    CategoriaInfortunio(String codice, Integer minIncluso, Integer maxEscluso, String descrizione) {
        this.codice = codice;
        this.minIncluso = minIncluso;
        this.maxEscluso = maxEscluso;
        this.descrizione = descrizione;
    }

    public String getCodice() { return codice; }
    public String getDescrizione(){ return descrizione; }

    /**
     * true se grado compreso [minIncluso, maxEscluso)
     **/
    public boolean contiene(Integer grado) {
        if (minIncluso != null && grado < minIncluso) return false;
        if (maxEscluso != null && grado >= maxEscluso) return false;
        return true;
    }

    /**
     * Dato il grado (anche null)
     * ritorna direttamente il codice ("FR","LQ","MT","GP")
     **/
    public static String codiceFromGrado(Integer grado, String dataMorte) {
        if (dataMorte != null && !dataMorte.trim().isEmpty()) {
            return MORTALE.codice;
        }

        if (grado == null) {
            logger.warn("ATTENZIONE: Grado nullo");
            return null;
        }

        for (CategoriaInfortunio c : values()) {
            if (c == MORTALE) continue;
            if (c.contiene(grado)) {
                return c.getCodice();
            }
        }
        logger.warn(String.format("Grado fuori range gestiti: ", grado));
        return null;
    }

    /**
     * Restituisce la descrizione a partire dal codice (es. "MO" -> "Mortale").
     **/
    public static String getDescrizioneFromCodice(String codice) {
        if (codice == null) return null;
        return Arrays.stream(values())
                .filter(c -> c.codice.equalsIgnoreCase(codice.trim()))
                .map(CategoriaInfortunio::getDescrizione)
                .findFirst()
                .orElse(null);
    }
}
