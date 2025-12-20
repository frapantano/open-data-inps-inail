package it.university.opendata.integration_backend.service;

import it.university.opendata.integration_backend.dto.inps.InpsPensioniTotaliDTO;

public interface InpsPensioniService {

    InpsPensioniTotaliDTO getTotali(Integer anno, String trimestre);
}