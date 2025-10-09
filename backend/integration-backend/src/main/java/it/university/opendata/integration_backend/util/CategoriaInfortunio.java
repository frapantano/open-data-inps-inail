package it.university.opendata.integration_backend.util;

public enum CategoriaInfortunio {

    MORTALE("MO", null, null),
    FRANCHIGIA("FR", -1, 5),             // [-1, 5)
    LIQUIDAZIONE("LQ", 5, 16),           // [5, 16)
    MEDIO_TEMPORANEO("MT", 16, 80),      // [16, 80)
    GRAVE_PERMANENTE("GP", 80, null);    // [80, +∞)

    private final String codice;
    private final Integer minIncluso;
    private final Integer maxEscluso;    // null = +∞

    CategoriaInfortunio(String codice, Integer minIncluso, Integer maxEscluso) {
        this.codice = codice;
        this.minIncluso = minIncluso;
        this.maxEscluso = maxEscluso;
    }

    public String getCode() { return codice; }

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
    public static String codiceFromGrado(Integer grado) {
        if (grado == null) {
            throw new IllegalArgumentException("Grado nullo");
        }
        for (CategoriaInfortunio c : values()) {
            if (c.contiene(grado)) {
                return c.getCode();
            }
        }
        throw new IllegalArgumentException("Grado fuori range gestiti: " + grado);
    }
}
