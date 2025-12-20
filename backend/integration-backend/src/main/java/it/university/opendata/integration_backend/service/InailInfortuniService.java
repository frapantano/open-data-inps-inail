package it.university.opendata.integration_backend.service;

import it.university.opendata.integration_backend.dto.inail.InailInfortuniTotaliDTO;

public interface InailInfortuniService {

    InailInfortuniTotaliDTO getTotali(Integer anno, String trimestre);
}
