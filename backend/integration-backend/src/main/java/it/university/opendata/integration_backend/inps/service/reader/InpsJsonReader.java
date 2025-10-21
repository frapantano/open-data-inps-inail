package it.university.opendata.integration_backend.inps.service.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.university.opendata.integration_backend.inps.dto.InpsJsonOpenData;
import it.university.opendata.integration_backend.inps.dto.InpsJsonRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class InpsJsonReader {

    private final ObjectMapper mapper;

    @Autowired
    public InpsJsonReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public InpsJsonOpenData readInpsRoot(InputStream is) throws IOException {
        return mapper.readValue(is, InpsJsonOpenData.class);
    }

    public List<InpsJsonRecord> readInpsRecords(InputStream is) throws IOException {
        return readInpsRoot(is).getDatastore().getData();
    }
}
