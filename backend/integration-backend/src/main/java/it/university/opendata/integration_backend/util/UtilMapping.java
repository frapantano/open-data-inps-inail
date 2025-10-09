package it.university.opendata.integration_backend.util;

public class UtilMapping {

    /** Dalla descrizione ritorna il codice 1..5; null se non mappabile. */
    public static Integer codiceInpsFromDescClasseEta(String descClasseEta) {
        if (descClasseEta == null || descClasseEta.isBlank())
            return null;

        switch (descClasseEta) {
            case "Fino a 54":
                return ClasseEta.ETA_FINO_A_54.getCode();
            case "55 - 59":
                return ClasseEta.ETA_55_59.getCode();
            case "60 - 64":
                return ClasseEta.ETA_60_64.getCode();
            case "65 - 67":
                return ClasseEta.ETA_65_67.getCode();
            case "68 e oltre":
                return ClasseEta.ETA_68_OLTRE.getCode();
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
                return "M";
            case "Femmine":
                return "F";
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
