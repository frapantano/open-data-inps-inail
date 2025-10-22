package it.university.opendata.integration_backend.util;

import java.util.Arrays;

public enum CategoriaPensione {

    ANTICIPATA("A", "Anticipata"),
    VECCHIAIA("V", "Vecchiaia"),
    SUPERSTITI("S", "Superstiti"),
    INVALIDITA("I", "Invalidita'");

    private final String codice;
    private final String descrizione;

    CategoriaPensione(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public String getCodice() { return codice; }
    public String getDescrizione(){ return descrizione; }

    /**
     * Restituisce la descrizione a partire dal codice (es. "A" -> "Anticipata").
     **/
    public static String getDescrizioneFromCodice(String codice) {
        if (codice == null) return null;
        return Arrays.stream(values())
                .filter(c -> c.codice.equalsIgnoreCase(codice.trim()))
                .map(CategoriaPensione::getDescrizione)
                .findFirst()
                .orElse(null);
    }

    /**
     * Restituisce il codice a partire dalla descrizione (es. "Vecchiaia" -> "V").
     **/
    public static String getCodiceFromDescrizione(String descrizione) {
        if (descrizione == null) return null;
        return Arrays.stream(values())
                .filter(c -> c.descrizione.equalsIgnoreCase(descrizione.trim()))
                .map(CategoriaPensione::getCodice)
                .findFirst()
                .orElse(null);
    }
}