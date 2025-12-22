package it.university.opendata.integration_backend.util.proiezioni;

public interface Key2Sum {
    Object getKey1();     // categoria
    Object getKey2();     // sesso / classeEta / regione
    Long getTotale();
}