package it.university.opendata.integration_backend.inps.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InpsJsonOpenData {

    private InpsJson datastore;  // vocabularies, metadata, data

    @Override
    public String toString() {
        return "InpsJsonOpenData{" +
                "datastore=" + datastore +
                '}';
    }
}
