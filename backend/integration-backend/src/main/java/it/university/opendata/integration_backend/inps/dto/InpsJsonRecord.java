package it.university.opendata.integration_backend.inps.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InpsJsonRecord {

    @JsonProperty("Anno_decorrenza")
    private Integer anno;

    @JsonProperty("SESSO")
    private String  sesso;                 // "Maschi"/"Femmine"

    @JsonProperty("tgestio")
    private String  tgestio;

    @JsonProperty("classe_eta")
    private String  classeEta;             // es. "55 - 59" (se presente)

    @JsonProperty("Categoria")
    private String  categoria;             // es. "Invalidità" / "Superstiti"

    @JsonProperty("Regione")
    private String  regione;

    @JsonProperty("Trimestrale")
    private String  trimestre;             // es. "I trimestre"

    @JsonProperty("Numero_pensioni")
    private Integer numeroPensioni;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InpsJsonRecord that = (InpsJsonRecord) o;
        return Objects.equals(getAnno(), that.getAnno()) &&
                Objects.equals(getSesso(), that.getSesso()) &&
                Objects.equals(getTgestio(), that.getTgestio()) &&
                Objects.equals(getClasseEta(), that.getClasseEta()) &&
                Objects.equals(getCategoria(), that.getCategoria()) &&
                Objects.equals(getRegione(), that.getRegione()) &&
                Objects.equals(getTrimestre(), that.getTrimestre()) &&
                Objects.equals(getNumeroPensioni(), that.getNumeroPensioni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnno(), getSesso(), getTgestio(), getClasseEta(), getCategoria(), getRegione(), getTrimestre(), getNumeroPensioni());
    }

    @Override
    public String toString() {
        return "InpsJsonRecord{" +
                "anno=" + anno +
                ", sesso='" + sesso + '\'' +
                ", tgestio='" + tgestio + '\'' +
                ", classeEta='" + classeEta + '\'' +
                ", categoria='" + categoria + '\'' +
                ", regione='" + regione + '\'' +
                ", trimestre='" + trimestre + '\'' +
                ", numeroPensioni=" + numeroPensioni +
                '}';
    }
}
