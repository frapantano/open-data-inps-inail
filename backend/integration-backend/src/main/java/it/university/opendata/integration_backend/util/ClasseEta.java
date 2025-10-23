package it.university.opendata.integration_backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public enum ClasseEta {

    ETA_FINO_A_54(1, null, 55, "Fino a 54"),     // [0, 55)
    ETA_55_59(2, 55, 60, "55 - 59"),           // [55, 60)
    ETA_60_64(3, 60, 65, "60 - 64"),           // [60, 65)
    ETA_65_67(4, 65, 68, "65 - 67"),           // [65, 68)
    ETA_68_OLTRE(5, 68, null, "68 e oltre");      // [68, +∞)

    private static final Logger logger = LoggerFactory.getLogger(ClasseEta.class);

    private final Integer codice;
    private final Integer minIncluso;    // null = -∞
    private final Integer maxEscluso;    // null = +∞
    private final String descrizione;

    ClasseEta(Integer codice, Integer minIncluso, Integer maxEscluso, String descrizione) {
        this.codice = codice;
        this.minIncluso = minIncluso;
        this.maxEscluso = maxEscluso;
        this.descrizione = descrizione;
    }

    public Integer getCodice() { return codice; }
    public String getDescrizione(){ return descrizione; }

    /**
     * true se grado compreso [minIncluso, maxEscluso)
     **/
    public boolean contiene(Integer eta) {
        if (minIncluso != null && eta < minIncluso) return false;
        if (maxEscluso != null && eta >= maxEscluso) return false;
        return true;
    }

    /**
     * Data l'eta' (anche null)
     * ritorna direttamente il codice
     **/
    public static Integer codiceDaEta(Integer eta) {
        if (eta == null) {
            logger.warn("ATTENZIONE: Eta' nulla");
            return null;
        }
        if (eta < 0) {
            logger.warn("ATTENZIONE: Eta' negativa: {}", eta);
            return null;
        }
        for (ClasseEta c : values()) {
            if (c.contiene(eta)) {
                return c.getCodice();
            }
        }
        logger.warn("ATTENZIONE: Eta' fuori range gestiti: {}", eta);
        return null;
    }

    /**
     * Dalla descrizione ritorna il codice 1..5; null se non mappabile.
     **/
    public static Integer codiceInpsFromDescrizione(String descClasseEta) {
        if (descClasseEta == null || descClasseEta.isBlank()) return null;
        return Arrays.stream(values())
                .filter(c -> c.descrizione.equalsIgnoreCase(descClasseEta.trim()))
                .map(ClasseEta::getCodice)
                .findFirst()
                .orElse(null);

    }

    /**
     * Restituisce la descrizione a partire dal codice (es. 1 -> "Fino a 54").
     **/
    public static String getDescrizioneFromCodice(Integer codice) {
        if (codice == null) return null;
        return Arrays.stream(values())
                .filter(c -> c.codice.equals(codice))
                .map(ClasseEta::getDescrizione)
                .findFirst()
                .orElse(null);
    }
}