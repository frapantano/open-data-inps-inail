package it.university.opendata.integration_backend.inps.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InpsJson {

    private Map<String, String> vocabularies;
    private Map<String, Object> metadata;
    private List<InpsJsonRecord> data;

    @Override
    public String toString() {
        return "InpsJson{" +
                "vocabularies=" + vocabularies +
                ", metadata=" + metadata +
                ", data=" + data +
                '}';
    }
}
