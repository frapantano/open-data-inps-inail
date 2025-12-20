package it.university.opendata.integration_backend.repositories.inps;

import it.university.opendata.integration_backend.entity.PensioniInps;
import it.university.opendata.integration_backend.util.proiezioni.KeySum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PensioniInpsRepository extends JpaRepository<PensioniInps, Long> {

    @Query("select coalesce(sum(p.numPensioni),0) " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre")
    long sumNumPensioniByAnnoAndTrimestre(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.sesso as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.sesso " +
            "order by p.sesso ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.classeEta as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.classeEta " +
            "order by p.classeEta ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaPensione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaPensione " +
            "order by p.categoriaPensione ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndCategoriaPensione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.regione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.regione " +
            "order by p.regione ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);
}
