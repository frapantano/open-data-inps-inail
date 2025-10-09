package it.university.opendata.integration_backend.inail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(name = "INFORTUNI_INAIL",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "ANNO","TRIMESTRE","REGIONE","SESSO","CLASSE_ETA","CATEGORIA_INFORTUNIO"
        }))
public class InfortuniInail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ANNO", nullable = false)
    private Integer anno;

    @Column(name = "TRIMESTRE", nullable = false, length = 3)               // "I","II","III","IV"
    private String trimestre;

    @Column(name = "REGIONE", nullable = false)                             // codice numerico ISTAT
    private String regione;

    @Column(name = "SESSO", nullable = false, length = 1)                   // 'M' = Maschio, 'F' = Femmina
    private String sesso;

    @Column(name = "CLASSE_ETA", nullable = false)                          // 1..5
    private Integer classeEta;

    @Column(name = "CATEGORIA_INFORTUNIO", nullable = false, length = 2)    //ENUM :MORTALE "MO", FRANCHIGIA "FR" [-1, 5), LIQUIDAZIONE "LQ" [5, 16), MEDIO_TEMPORANEO "MT" [16, 80), GRAVE_PERMANENTE "GP" [80, +∞)
    private String categoriaInfortunio;

    @Column(name = "GESTIONE", nullable = false, length = 4)                //Codice Ateco
    private String gestione;

    @Column(name = "NUM_INFORTUNI", nullable = false)
    private Integer numInfortuni;

    public InfortuniInail() {
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

    public String getCategoriaInfortunio() {
        return categoriaInfortunio;
    }

    public void setCategoriaInfortunio(String categoriaInfortunio) {
        this.categoriaInfortunio = categoriaInfortunio;
    }

    public String getGestione() {
        return gestione;
    }

    public void setGestione(String gestione) {
        this.gestione = gestione;
    }

    public Integer getNumInfortuni() {
        return numInfortuni;
    }

    public void setNumInfortuni(Integer numInfortuni) {
        this.numInfortuni = numInfortuni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfortuniInail that = (InfortuniInail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(getAnno(), that.getAnno()) &&
                Objects.equals(getTrimestre(), that.getTrimestre()) &&
                Objects.equals(getRegione(), that.getRegione()) &&
                Objects.equals(getSesso(), that.getSesso()) &&
                Objects.equals(getClasseEta(), that.getClasseEta()) &&
                Objects.equals(getCategoriaInfortunio(), that.getCategoriaInfortunio()) &&
                Objects.equals(getGestione(), that.getGestione()) &&
                Objects.equals(getNumInfortuni(), that.getNumInfortuni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getAnno(), getTrimestre(), getRegione(), getSesso(), getClasseEta(), getCategoriaInfortunio(), getGestione(), getNumInfortuni());
    }

    @Override
    public String toString() {
        return "InfortuniInail{" +
                "id=" + id +
                ", anno=" + anno +
                ", trimestre='" + trimestre + '\'' +
                ", regione='" + regione + '\'' +
                ", sesso='" + sesso + '\'' +
                ", classeEta=" + classeEta +
                ", categoriaInfortunio='" + categoriaInfortunio + '\'' +
                ", gestione='" + gestione + '\'' +
                ", numInfortuni=" + numInfortuni +
                '}';
    }
}
