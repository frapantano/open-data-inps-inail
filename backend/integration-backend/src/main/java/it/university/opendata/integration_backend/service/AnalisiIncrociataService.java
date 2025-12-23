package it.university.opendata.integration_backend.service;

import it.university.opendata.integration_backend.dto.composizioni.AnalisiIncrociataDTO;
import it.university.opendata.integration_backend.dto.composizioni.CrossInfortuniMedioGraviPensioneInvaliditaDTO;
import it.university.opendata.integration_backend.dto.composizioni.CrossInfortuniMortaliPensioneSuperstitiDTO;

public interface AnalisiIncrociataService {

    AnalisiIncrociataDTO getComposizioniSesso(Integer anno, String trimestre, String codSesso);

    AnalisiIncrociataDTO getComposizioniClasseEta(Integer anno, String trimestre, Integer codClasseEta);

    AnalisiIncrociataDTO getComposizioniRegione(Integer anno, String trimestre, String codIstatRegione);

    CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoVsSuperstitiSesso(Integer anno, String trimestre, String codSesso);

    CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoSuperstitiClasseEta(Integer anno, String trimestre, Integer codClasseEta);

    CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoSuperstitiRegione(Integer anno, String trimestre, String codIstatRegione);

    CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaSesso(Integer anno, String trimestre, String codSesso);

    CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaClasseEta(Integer anno, String trimestre, Integer codClasseEta);

    CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaRegione(Integer anno, String trimestre, String codIstatRegione);
}
