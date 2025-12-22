package it.university.opendata.integration_backend;

import it.university.opendata.integration_backend.util.Regioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class EtlRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EtlRunner.class);

    private static final int ANNO = 2024;
    private static final String TRIMESTRE = "I";
    private static final List<String> REGIONI = new ArrayList<>(Regioni.getTutteDescrizioni());

    @Autowired
    private EtlRunnerHelperService helperService;

    @Override
    public void run(String... args) throws IOException {
        helperService.caricaDataset(ANNO, TRIMESTRE, REGIONI);
        logger.info("Dataset INPS e INAIL caricato.");
    }

}