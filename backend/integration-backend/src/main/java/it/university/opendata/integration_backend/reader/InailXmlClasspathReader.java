package it.university.opendata.integration_backend.reader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import it.university.opendata.integration_backend.dto.inail.payload.InfortuniXml;
import it.university.opendata.integration_backend.dto.inail.payload.InfortunioXmlDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class InailXmlClasspathReader {

    private static final Logger logger = LoggerFactory.getLogger(InailXmlClasspathReader.class);

    private final XmlMapper xml;

    public InailXmlClasspathReader() {
        this.xml = new XmlMapper();
    }

    /**
     * Legge un singolo XML dal classpath e ritorna l'elenco dei record.
     */
    public List<InfortunioXmlDTO> readFromClasspath(String path) throws IOException {
        logger.info("Cerco risorsa sul classpath: {}", path);

        ClassPathResource res = new ClassPathResource(path);
        if (!res.exists()) {
            logger.error("XML non trovato sul classpath: {}", path);
            return List.of();
        }

        logger.info("Resource URL: {}", res.getURL());
        try (InputStream is = res.getInputStream()) {
            InfortuniXml root = xml.readValue(is, InfortuniXml.class);
            List<InfortunioXmlDTO> out = (root != null) ? root.getListaInfortuni() : null;
            if (out == null) out = java.util.Collections.emptyList();
            logger.info("XML '{}' -> {} record", res.getFilename(), out.size());
            return out;
        } catch (Exception e) {
            logger.error("Errore leggendo XML '{}'", path, e);
            return List.of();
        }
    }
}
