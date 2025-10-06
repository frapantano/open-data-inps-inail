package it.university.opendata.integration_backend.util;

import java.text.Normalizer;
import java.util.Map;

public final class RegioniSingleton {

    private RegioniSingleton() {}

    private static final Map<String,String> NAME_TO_CODE = Map.ofEntries(
            Map.entry("piemonte", "01"),
            Map.entry("valle d aosta", "02"),
            Map.entry("lombardia", "03"),
            Map.entry("trentino alto adige", "04"),
            Map.entry("veneto", "05"),
            Map.entry("friuli venezia giulia", "06"),
            Map.entry("liguria", "07"),
            Map.entry("emilia romagna", "08"),
            Map.entry("toscana", "09"),
            Map.entry("umbria", "10"),
            Map.entry("marche", "11"),
            Map.entry("lazio", "12"),
            Map.entry("abruzzo", "13"),
            Map.entry("molise", "14"),
            Map.entry("campania", "15"),
            Map.entry("puglia", "16"),
            Map.entry("basilicata", "17"),
            Map.entry("calabria", "18"),
            Map.entry("sicilia", "19"),
            Map.entry("sardegna", "20")
    );

    // minuscole, rimozione accenti, punteggiatura -> spazio, spazi compressi
    private static String normalizza(String s) {
        String t = Normalizer.normalize(s.trim().toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        t = t.replaceAll("[^a-z0-9\\s]", " ");
        t = t.replaceAll("\\s+", " ").trim();
        return t;
    }

    /** Normalizza solo "Piemonte e Valle d'Aosta" -> "Piemonte" */
    private static String normalizzaCasoCompostoToPiemonte(String name) {
        String n = normalizza(name);
        if (n.equals("piemonte e valle d aosta")) return "Piemonte";
        return name.trim();
    }

    /** Ritorna codice "01".."20" o null se non mappabile. */
    public static String codiceFromNomeRegione(String nomeRegione) {
        if (nomeRegione == null || nomeRegione.isBlank())
            return null;

        String nomeNormalizzato = normalizza(normalizzaCasoCompostoToPiemonte(nomeRegione));
        String code = NAME_TO_CODE.get(nomeNormalizzato);

        return code;
    }

    /*
    String code = RegioniSingleton.codiceFromNomeRegione(r.getRegione());
    if (code == null) {
        log.warn("Regione non mappata, salto record: '{}'", r.getRegione());
        return; // skip
    }
     */
}
