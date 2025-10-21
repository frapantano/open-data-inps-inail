package it.university.opendata.integration_backend.inps;

import it.university.opendata.integration_backend.inps.dto.InpsJsonRecord;
import it.university.opendata.integration_backend.inps.dto.InpsKey;
import it.university.opendata.integration_backend.inps.entity.PensioniInps;
import it.university.opendata.integration_backend.inps.repo.PensioniInpsRepository;
import it.university.opendata.integration_backend.inps.service.InpsMapper;
import it.university.opendata.integration_backend.inps.service.reader.InpsJsonClasspathReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InpsEtlRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InpsEtlRunner.class);

    @Autowired
    private InpsJsonClasspathReader reader;

    @Autowired
    private InpsMapper mapper;

    @Autowired
    private PensioniInpsRepository repository;

    @Override
    public void run(String... args) throws IOException {
        String path = "datasets/2024/primoSemestre/inps/pensioniItalia.json";
        List<InpsJsonRecord> records = reader.readFromClasspath(path);
        if (records == null) {
            logger.error("ATTENZIONE: File JSON non trovato o vuoto!");
            return;
        }

        Map<InpsKey, Double> totaliAggregati =  records.stream()
                .collect(Collectors.groupingBy(
                        mapper::inpsKeyOf,
                        Collectors.summingDouble(InpsJsonRecord::getNumeroPensioni)
                ));

        List<PensioniInps> entities = totaliAggregati.entrySet().stream()
                        .map(e -> mapper.toInpsEntity(e.getKey(), e.getValue()))
                        .collect(Collectors.toList());

        repository.saveAll(entities);
        logger.info("ETL completato: " + entities.size() + " record caricati.");
    }
}
