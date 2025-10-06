package it.university.opendata.integration_backend.util;

public class UtilMapping {

    /** Dalla descrizione ritorna il codice 1..5; null se non mappabile. */
    public static Integer codiceInpsFromDescClasseEta(String descClasseEta) {
        if (descClasseEta == null || descClasseEta.isBlank())
            return null;

        switch (descClasseEta) {
            case "Fino a 54":
                return 1;
            case "55 - 59":
                return 2;
            case "60 - 64":
                return 3;
            case "65 - 67":
                return 4;
            case "68 e oltre":
                return 5;
            default:
                return null; // gestisci skip+WARN nel service
        }
    }

    /** Dalla descrizione ritorna il codice S / N; null se non mappabile. */
    public static String codiceInpsFromDescSesso(String descSesso) {
        if (descSesso == null || descSesso.isBlank())
            return null;

        switch (descSesso) {
            case "Maschi":
                return "S";
            case "Femmine":
                return "N";
            default:
                return null; // gestisci skip+WARN nel service
        }
    }

    /** Dalla descrizione ritorna il codice I / S; null se non mappabile. */
    public static String codiceInpsFromDescPensione(String descPensione) {
        if (descPensione == null || descPensione.isBlank())
            return null;

        switch (descPensione) {
            case "Invalidita'":
                return "I";
            case "Superstiti":
                return "S";
            default:
                return null; // gestisci skip+WARN nel service
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
                return null; // gestisci skip+WARN nel service
        }
    }
}
