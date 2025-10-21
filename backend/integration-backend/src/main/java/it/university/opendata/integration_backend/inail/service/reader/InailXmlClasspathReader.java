package it.university.opendata.integration_backend.inail.service.reader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InailXmlClasspathReader {

    private static final Logger logger = LoggerFactory.getLogger(InailXmlClasspathReader.class);

    private final XmlMapper xml;

    public InailXmlClasspathReader() {
        this.xml = new XmlMapper();
    }
}
