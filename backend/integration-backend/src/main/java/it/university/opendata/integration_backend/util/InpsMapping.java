package it.university.opendata.integration_backend.util;

public class InpsMapping {

    /** Dalla descrizione ritorna il codice 1..5; null se non mappabile. */
    public static Integer codiceInpsFromDescClasseEta(String descClasseEta) {
        if (descClasseEta == null || descClasseEta.isBlank())
            return null;

        switch (descClasseEta) {
            case "Fino a 54":
                return ClasseEta.ETA_FINO_A_54.getCodice();
            case "55 - 59":
                return ClasseEta.ETA_55_59.getCodice();
            case "60 - 64":
                return ClasseEta.ETA_60_64.getCodice();
            case "65 - 67":
                return ClasseEta.ETA_65_67.getCodice();
            case "68 e oltre":
                return ClasseEta.ETA_68_OLTRE.getCodice();
            default:
                return null;
        }
    }

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

    /** Dalla descrizione ritorna il codice I / S; null se non mappabile. */
    public static String codiceInpsFromDescPensione(String descPensione) {
        if (descPensione == null || descPensione.isBlank())
            return null;

        switch (descPensione) {
            case "Invalidita'":
                return "I";
            case "Superstiti":
                return "S";
            case "Vecchiaia":
                return "V";
            case "Anticipata":
                return "A";
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
}
