package it.university.opendata.integration_backend.util;

import java.util.Arrays;

public enum Regioni {

    PIEMONTE("01", "Piemonte"),
    VALLE_D_AOSTA("02", "Valle d'Aosta"),
    LOMBARDIA("03", "Lombardia"),
    TRENTINO_ALTO_ADIGE("04", "Trentino Alto Adige"),
    VENETO("05", "Veneto"),
    FRIULI_VENEZIA_GIULIA("06", "Friuli Venezia Giulia"),
    LIGURIA("07", "Liguria"),
    EMILIA_ROMAGNA("08", "Emilia Romagna"),
    TOSCANA("09", "Toscana"),
    UMBRIA("10", "Umbria"),
    MARCHE("11", "Marche"),
    LAZIO("12", "Lazio"),
    ABRUZZO("13", "Abruzzo"),
    MOLISE("14", "Molise"),
    CAMPANIA("15", "Campania"),
    PUGLIA("16", "Puglia"),
    BASILICATA("17", "Basilicata"),
    CALABRIA("18", "Calabria"),
    SICILIA("19", "Sicilia"),
    SARDEGNA("20", "Sardegna");

    private final String codice;
    private final String descrizione;

    Regioni(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public String getCodice() { return codice; }
    public String getDescrizione(){ return descrizione; }

    /**
     * Restituisce la descrizione a partire dal codice (es. "01" -> "Piemonte").
     **/
    public static String getDescrizioneFromCodice(String codice) {
        if (codice == null) return null;
        return Arrays.stream(values())
                .filter(c -> c.codice.equalsIgnoreCase(codice.trim()))
                .map(Regioni::getDescrizione)
                .findFirst()
                .orElse(null);
    }

    /**
     * Restituisce il codice a partire dalla descrizione (es. "Piemonte" -> "01").
     **/
    public static String getCodiceFromDescrizione(String descrizione) {
        if (descrizione == null) return null;
        if(descrizione.equalsIgnoreCase("Piemonte e Valle d'Aosta")) return PIEMONTE.codice;
        return Arrays.stream(values())
                .filter(c -> c.descrizione.equalsIgnoreCase(descrizione.trim()))
                .map(Regioni::getCodice)
                .findFirst()
                .orElse(null);
    }
}
