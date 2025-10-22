package it.university.opendata.integration_backend.inps.repo.proiezioni;

public interface KeySum {
    Object getKey();    // può essere String (sesso/regione/categoria) o Integer (classeEta)
    Long getTotale();
}
