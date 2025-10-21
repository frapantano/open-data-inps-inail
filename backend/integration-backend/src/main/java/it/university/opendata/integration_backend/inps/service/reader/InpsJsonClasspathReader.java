package it.university.opendata.integration_backend.inps.service.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.university.opendata.integration_backend.inps.dto.InpsJsonRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InpsJsonClasspathReader extends InpsJsonReader {

    private static final Logger logger = LoggerFactory.getLogger(InpsJsonClasspathReader.class);

    @Autowired
    public InpsJsonClasspathReader(ObjectMapper mapper) { super(mapper); }

    public List<InpsJsonRecord> readFromClasspath(String path) throws IOException {
        logger.info("Cerco risorsa sul classpath: {}", path);
        ClassPathResource res = new ClassPathResource(path);
        if (!res.exists()) {
            return null;
        }
        logger.info("Resource URL: {}", res.getURL());
        try (InputStream is = res.getInputStream()) {
            List<InpsJsonRecord> out = readInpsRecords(is);
            logger.info("Record letti: {}", out != null ? out.size() : 0);
            return out;
        }
    }
}
