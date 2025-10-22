package it.university.opendata.integration_backend.inail.repo;

import it.university.opendata.integration_backend.inail.entity.InfortuniInail;
import it.university.opendata.integration_backend.util.proiezioni.KeySum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfortuniInailRepository extends JpaRepository<InfortuniInail, Long> {

    @Query("select coalesce(sum(p.numInfortuni),0) " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre")
    long sumNumInfortuniByAnnoAndTrimestre(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.sesso as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.sesso " +
            "order by p.sesso ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.classeEta as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.classeEta " +
            "order by p.classeEta ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaInfortunio as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaInfortunio " +
            "order by p.categoriaInfortunio ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndCategoriaInfortunio(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.regione as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.regione " +
            "order by p.regione ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

}
