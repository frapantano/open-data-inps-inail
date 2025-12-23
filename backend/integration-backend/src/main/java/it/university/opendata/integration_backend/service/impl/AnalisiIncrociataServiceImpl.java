package it.university.opendata.integration_backend.service.impl;

import it.university.opendata.integration_backend.dto.composizioni.AnalisiIncrociataDTO;
import it.university.opendata.integration_backend.dto.composizioni.CrossInfortuniMedioGraviPensioneInvaliditaDTO;
import it.university.opendata.integration_backend.dto.composizioni.CrossInfortuniMortaliPensioneSuperstitiDTO;
import it.university.opendata.integration_backend.repositories.InfortuniInailRepository;
import it.university.opendata.integration_backend.repositories.PensioniInpsRepository;
import it.university.opendata.integration_backend.service.AnalisiIncrociataService;
import it.university.opendata.integration_backend.util.ClasseEta;
import it.university.opendata.integration_backend.util.Regioni;
import it.university.opendata.integration_backend.util.UtilMapping;
import it.university.opendata.integration_backend.util.proiezioni.KeySum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalisiIncrociataServiceImpl implements AnalisiIncrociataService {

    private final PensioniInpsRepository inpsRepository;

    private final InfortuniInailRepository inailRepository;

    public AnalisiIncrociataServiceImpl(PensioniInpsRepository inpsRepository, InfortuniInailRepository inailRepository) {
        this.inpsRepository = inpsRepository;
        this.inailRepository = inailRepository;
    }

    @Override
    public AnalisiIncrociataDTO getComposizioniSesso(Integer anno, String trimestre, String codSesso) {
        List<KeySum> pensioniPerSesso = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndSesso(anno, trimestre, codSesso);
        List<KeySum> infortuniPerSesso = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndSesso(anno, trimestre, codSesso);

        Map<String, Long> inailMap = UtilMapping.toMap(infortuniPerSesso);
        Map<String, Long> inpsMap = UtilMapping.toMap(pensioniPerSesso);

        AnalisiIncrociataDTO dto = new AnalisiIncrociataDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("SESSO");
        dto.setGroupByValue(codSesso);
        dto.setInail(inailMap);
        dto.setInps(inpsMap);

        return dto;
    }

    @Override
    public AnalisiIncrociataDTO getComposizioniClasseEta(Integer anno, String trimestre, Integer codClasseEta) {
        List<KeySum> pensioniPerClasseEta = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndClasseEta(anno, trimestre, codClasseEta);
        List<KeySum> infortuniPerClasseEta = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndClasseEta(anno, trimestre, codClasseEta);

        Map<String, Long> inailMap = UtilMapping.toMap(infortuniPerClasseEta);
        Map<String, Long> inpsMap = UtilMapping.toMap(pensioniPerClasseEta);

        AnalisiIncrociataDTO dto = new AnalisiIncrociataDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("CLASSE_ETA");
        dto.setGroupByValue(ClasseEta.getDescrizioneFromCodice(codClasseEta));
        dto.setInail(inailMap);
        dto.setInps(inpsMap);

        return dto;
    }

    @Override
    public AnalisiIncrociataDTO getComposizioniRegione(Integer anno, String trimestre, String codIstatRegione) {
        List<KeySum> pensioniPerRegione = inpsRepository.sumNumPensioniByAnnoAndTrimestreAndRegione(anno, trimestre, codIstatRegione);
        List<KeySum> infortuniPerRegione = inailRepository.sumNumInfortuniByAnnoAndTrimestreAndRegione(anno, trimestre, codIstatRegione);

        Map<String, Long> inailMap = UtilMapping.toMap(infortuniPerRegione);
        Map<String, Long> inpsMap = UtilMapping.toMap(pensioniPerRegione);

        AnalisiIncrociataDTO dto = new AnalisiIncrociataDTO();
        dto.setAnno(anno);
        dto.setTrimestre(trimestre);
        dto.setGroupBy("REGIONE");
        dto.setGroupByValue(Regioni.getDescrizioneFromCodice(codIstatRegione));
        dto.setInail(inailMap);
        dto.setInps(inpsMap);

        return dto;
    }

    @Override
    public CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoVsSuperstitiSesso(Integer anno, String trimestre, String codSesso) {
        Long mo = inailRepository.sumInfortuniMortali(anno, trimestre, null, codSesso, null);
        Long sup = inpsRepository.sumPensioniSuperstiti(anno, trimestre, null, codSesso, null);

        return new CrossInfortuniMortaliPensioneSuperstitiDTO(anno, trimestre, "SESSO", codSesso, mo, sup);
    }

    @Override
    public CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoSuperstitiClasseEta(Integer anno, String trimestre, Integer codClasseEta) {
        Long mo = inailRepository.sumInfortuniMortali(anno, trimestre, null, null, codClasseEta);
        Long sup = inpsRepository.sumPensioniSuperstiti(anno, trimestre, null, null, codClasseEta);

        return new CrossInfortuniMortaliPensioneSuperstitiDTO(anno, trimestre, "CLASSE_ETA", ClasseEta.getDescrizioneFromCodice(codClasseEta), mo, sup);
    }

    @Override
    public CrossInfortuniMortaliPensioneSuperstitiDTO getInfMoSuperstitiRegione(Integer anno, String trimestre, String codIstatRegione) {
        Long mo = inailRepository.sumInfortuniMortali(anno, trimestre, codIstatRegione, null, null);
        Long sup = inpsRepository.sumPensioniSuperstiti(anno, trimestre, codIstatRegione, null, null);

        return new CrossInfortuniMortaliPensioneSuperstitiDTO(anno, trimestre, "REGIONE", Regioni.getDescrizioneFromCodice(codIstatRegione), mo, sup);
    }

    @Override
    public CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaSesso(Integer anno, String trimestre, String codSesso) {
        Long mtGp = inailRepository.sumInfortuniMedioGrave(anno, trimestre, null, codSesso, null);
        Long inv = inpsRepository.sumPensioniInvalidita(anno, trimestre, null, codSesso, null);

        return new CrossInfortuniMedioGraviPensioneInvaliditaDTO(anno, trimestre, "SESSO", codSesso, mtGp, inv);
    }

    @Override
    public CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaClasseEta(Integer anno, String trimestre, Integer codClasseEta) {
        Long mtGp = inailRepository.sumInfortuniMedioGrave(anno, trimestre, null, null, codClasseEta);
        Long inv = inpsRepository.sumPensioniInvalidita(anno, trimestre, null, null, codClasseEta);

        return new CrossInfortuniMedioGraviPensioneInvaliditaDTO(anno, trimestre, "CLASSE_ETA", ClasseEta.getDescrizioneFromCodice(codClasseEta), mtGp, inv);
    }

    @Override
    public CrossInfortuniMedioGraviPensioneInvaliditaDTO getInfMtGpVsInvaliditaRegione(Integer anno, String trimestre, String codIstatRegione) {
        Long mtGp = inailRepository.sumInfortuniMedioGrave(anno, trimestre, codIstatRegione, null, null);
        Long inv = inpsRepository.sumPensioniInvalidita(anno, trimestre, codIstatRegione, null, null);

        return new CrossInfortuniMedioGraviPensioneInvaliditaDTO(anno, trimestre, "REGIONE", Regioni.getDescrizioneFromCodice(codIstatRegione), mtGp, inv);
    }
}
