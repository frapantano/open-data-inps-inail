package it.university.opendata.integration_backend.util;

import it.university.opendata.integration_backend.util.proiezioni.KeySum;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UtilMapping {

    /** Dalla descrizione ritorna il codice S / M; null se non mappabile. */
    public static String codiceInpsFromDescSesso(String descSesso) {
        if (descSesso == null || descSesso.isBlank())
            return null;

        switch (descSesso) {
            case "Maschi":
                return "M";
            case "Femmine":
                return "F";
            default:
                return null;
        }
    }

    /** Ritorna "I","II","III","IV" oppure null se non mappabile. */
    public static String trimestreToRoman(String descTrimestre) {
        if (descTrimestre == null || descTrimestre.isBlank())
            return null;

        switch (descTrimestre) {
            case "I trimestre":
                return "I";
            case "II trimestre":
                return "II";
            case "III trimestre":
                return "III";
            case "IV trimestre":
                return "IV";
            default:
                return null;
        }
    }

    public static String romanToTrimestre(String descTrimestre) {
        if (descTrimestre == null || descTrimestre.isBlank())
            return null;

        switch (descTrimestre) {
            case "I":
                return "primoTrimestre";
            case "II":
                return "secondoTrimestre";
            case "III":
                return "terzoTrimestre";
            case "IV":
                return "quartoTrimestre";
            default:
                return null;
        }
    }

    public static String normalizzaDescRegione(String regione) {
        return regione.trim().replaceAll("[\\s'’]+", "");
    }

    public static Map<String, Long> toMap(List<KeySum> list) {
        Map<String, Long> map = new LinkedHashMap<>();

        if (list == null) {
            return map;
        }

        for (KeySum ks : list) {
            String key = String.valueOf(ks.getKey());
            Long value = ks.getTotale() != null ? ks.getTotale() : 0L;
            map.put(key, value);
        }

        return map;
    }
}
