package it.university.opendata.integration_backend.inps.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PENSIONI_INPS",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "ANNO","TRIMESTRE","REGIONE","SESSO","CLASSE_ETA","CATEGORIA_PENSIONE"
        }))
public class PensioniInps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ANNO", nullable = false)
    private Integer anno;

    @Column(name = "TRIMESTRE", nullable = false, length = 3)           // "I","II","III","IV"
    private String trimestre;

    @Column(name = "REGIONE", nullable = false)                         // codice numerico ISTAT
    private String regione;

    @Column(name = "SESSO", nullable = false, length = 1)               // 'M' = Maschio, 'F' = Femmina
    private String sesso;

    @Column(name = "CLASSE_ETA", nullable = false)                      // 1..5
    private Integer classeEta;

    @Column(name = "CATEGORIA_PENSIONE", nullable = false, length = 1)  // 'I' = Invalidità /'S' = Superstiti
    private String categoriaPensione;

    @Column(name = "GESTIONE", nullable = false, length = 4)            //Codice Ateco
    private String gestione;

    @Column(name = "NUM_PENSIONI", nullable = false)
    private Integer numPensioni;

    public PensioniInps() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public Integer getClasseEta() {
        return classeEta;
    }

    public void setClasseEta(Integer classeEta) {
        this.classeEta = classeEta;
    }

    public String getCategoriaPensione() {
        return categoriaPensione;
    }

    public void setCategoriaPensione(String categoriaPensione) {
        this.categoriaPensione = categoriaPensione;
    }

    public Integer getNumPensioni() {
        return numPensioni;
    }

    public void setNumPensioni(Integer numPensioni) {
        this.numPensioni = numPensioni;
    }

    public String getGestione() {
        return gestione;
    }

    public void setGestione(String gestione) {
        this.gestione = gestione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PensioniInps that = (PensioniInps) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAnno(), that.getAnno()) &&
                Objects.equals(getTrimestre(), that.getTrimestre()) &&
                Objects.equals(getRegione(), that.getRegione()) &&
                Objects.equals(getSesso(), that.getSesso()) &&
                Objects.equals(getClasseEta(), that.getClasseEta()) &&
                Objects.equals(getCategoriaPensione(), that.getCategoriaPensione()) &&
                Objects.equals(getGestione(), that.getGestione()) &&
                Objects.equals(getNumPensioni(), that.getNumPensioni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnno(), getTrimestre(), getRegione(), getSesso(), getClasseEta(), getCategoriaPensione(), getGestione(), getNumPensioni());
    }

    @Override
    public String toString() {
        return "PensioniInps{" +
                "id=" + id +
                ", anno=" + anno +
                ", trimestre='" + trimestre + '\'' +
                ", regione='" + regione + '\'' +
                ", sesso='" + sesso + '\'' +
                ", classeEta=" + classeEta +
                ", categoriaPensione='" + categoriaPensione + '\'' +
                ", gestione='" + gestione + '\'' +
                ", numPensioni=" + numPensioni +
                '}';
    }
}
