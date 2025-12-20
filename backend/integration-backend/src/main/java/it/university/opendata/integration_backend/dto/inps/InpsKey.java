package it.university.opendata.integration_backend.dto.inps;

import java.util.Objects;

public class InpsKey {

    private final int anno;
    private final String trimestre;
    private final String regione;
    private final String sesso;
    private final int classeEta;
    private final String categoria;

    public InpsKey(int anno, String trimestre, String regione,
                   String sesso, int classeEta, String categoria) {
        this.anno = anno;
        this.trimestre = trimestre;
        this.regione = regione;
        this.sesso = sesso;
        this.classeEta = classeEta;
        this.categoria = categoria;
    }

    public int getAnno() { return anno; }
    public String getTrimestre() { return trimestre; }
    public String getRegione() { return regione; }
    public String getSesso() { return sesso; }
    public int getClasseEta() { return classeEta; }
    public String getCategoria() { return categoria; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InpsKey)) return false;
        InpsKey key = (InpsKey) o;
        return anno == key.anno &&
                classeEta == key.classeEta &&
                Objects.equals(trimestre, key.trimestre) &&
                Objects.equals(regione, key.regione) &&
                Objects.equals(sesso, key.sesso) &&
                Objects.equals(categoria, key.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anno, trimestre, regione, sesso, classeEta, categoria);
    }

    @Override
    public String toString() {
        return "InpsKey{" +
                "anno=" + anno +
                ", trimestre='" + trimestre + '\'' +
                ", regione='" + regione + '\'' +
                ", sesso='" + sesso + '\'' +
                ", classeEta=" + classeEta +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
