package it.university.opendata.integration_backend.util;

public enum ClasseEta {

    ETA_FINO_A_54(1, null, 55),     // [0, 55)
    ETA_55_59(2, 55, 60),           // [55, 60)
    ETA_60_64(3, 60, 65),           // [60, 65)
    ETA_65_67(4, 65, 68),           // [65, 68)
    ETA_68_OLTRE(5, 68, null);      // [68, +∞)

    private final Integer codice;
    private final Integer minIncluso;    // null = -∞
    private final Integer maxEscluso;    // null = +∞

    ClasseEta(Integer codice, Integer minIncluso, Integer maxEscluso) {
        this.codice = codice;
        this.minIncluso = minIncluso;
        this.maxEscluso = maxEscluso;
    }

    public Integer getCode() { return codice; }

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
    public static Integer codiceFromGrado(Integer eta) {
        if (eta == null) {
            throw new IllegalArgumentException("Eta nulla");
        }
        if (eta < 0) {
            throw new IllegalArgumentException("Età negativa: " + eta);
        }
        for (ClasseEta c : values()) {
            if (c.contiene(eta)) {
                return c.getCode();
            }
        }
        throw new IllegalArgumentException("Eta fuori range gestiti: " + eta);
    }
}
