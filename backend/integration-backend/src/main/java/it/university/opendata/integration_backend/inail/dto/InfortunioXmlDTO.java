package it.university.opendata.integration_backend.inail.dto;

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
public class InfortunioXmlDTO {

    @JsonProperty("DataMorte")
    private String dataMorte;

    @JsonProperty("Genere")
    private String genere;

    @JsonProperty("Eta")
    private String eta;

    @JsonProperty("GradoMenomazione")
    private String gradoMenomazione;

    @JsonProperty("SettoreAttivitaEconomica")
    private String settoreAttivitaEconomica;

    @JsonProperty("IdentificativoCaso")
    private String identificativoCaso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfortunioXmlDTO that = (InfortunioXmlDTO) o;
        return Objects.equals(getDataMorte(), that.getDataMorte()) &&
                Objects.equals(getGenere(), that.getGenere()) &&
                Objects.equals(getEta(), that.getEta()) &&
                Objects.equals(getGradoMenomazione(), that.getGradoMenomazione()) &&
                Objects.equals(getSettoreAttivitaEconomica(), that.getSettoreAttivitaEconomica()) &&
                Objects.equals(getIdentificativoCaso(), that.getIdentificativoCaso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataMorte(), getGenere(), getEta(), getGradoMenomazione(), getSettoreAttivitaEconomica(), getIdentificativoCaso());
    }

    @Override
    public String toString() {
        return "InfortunioXmlDTO{" +
                "dataMorte='" + dataMorte + '\'' +
                ", genere='" + genere + '\'' +
                ", eta='" + eta + '\'' +
                ", gradoMenomazione='" + gradoMenomazione + '\'' +
                ", settoreAttivitaEconomica='" + settoreAttivitaEconomica + '\'' +
                ", identificativoCaso='" + identificativoCaso + '\'' +
                '}';
    }
}
