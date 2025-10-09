package it.university.opendata.integration_backend.inail.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfortuniXml {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("DatiConCadenzaSemestraleInfortunio")
    private List<InfortunioXmlDTO> listaInfortuni;

    public List<InfortunioXmlDTO> getItems() { return listaInfortuni; }
    public void setItems(List<InfortunioXmlDTO> listaInfortuni) { this.listaInfortuni = listaInfortuni; }
}
