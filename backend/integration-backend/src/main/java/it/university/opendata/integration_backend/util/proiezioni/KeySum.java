package it.university.opendata.integration_backend.util.proiezioni;

public interface KeySum {
    Object getKey();    // può essere String (sesso/regione/categoria) o Integer (classeEta)
    Long getTotale();
}
