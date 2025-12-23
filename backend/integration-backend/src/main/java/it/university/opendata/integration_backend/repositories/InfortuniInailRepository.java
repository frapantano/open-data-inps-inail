package it.university.opendata.integration_backend.repositories;

import it.university.opendata.integration_backend.entity.InfortuniInail;
import it.university.opendata.integration_backend.util.proiezioni.Key2Sum;
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
            "order by coalesce(sum(p.numInfortuni),0) desc, p.sesso asc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.classeEta as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.classeEta " +
            "order by coalesce(sum(p.numInfortuni),0) desc, p.classeEta asc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaInfortunio as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaInfortunio " +
            "order by coalesce(sum(p.numInfortuni),0) desc, p.categoriaInfortunio asc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndCategoriaInfortunio(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.regione as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.regione " +
            "order by coalesce(sum(p.numInfortuni),0) desc, p.regione asc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaInfortunio as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.sesso = :sesso " +
            "group by p.categoriaInfortunio " +
            "order by coalesce(sum(p.numInfortuni),0) desc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("sesso") String sesso);

    @Query("select p.categoriaInfortunio as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.classeEta = :classeEta " +
            "group by p.categoriaInfortunio " +
            "order by coalesce(sum(p.numInfortuni),0) desc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("classeEta") Integer classeEta);

    @Query("select p.categoriaInfortunio as key, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.regione = :regione " +
            "group by p.categoriaInfortunio " +
            "order by coalesce(sum(p.numInfortuni),0) desc ")
    List<KeySum> sumNumInfortuniByAnnoAndTrimestreAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("regione") String regione);

    @Query("select p.categoriaInfortunio as key1, p.sesso as key2, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaInfortunio, p.sesso " +
            "order by p.categoriaInfortunio asc, p.sesso asc, coalesce(sum(p.numInfortuni),0) desc ")
    List<Key2Sum> sumByCategoriaAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaInfortunio as key1, p.classeEta as key2, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaInfortunio, p.classeEta " +
            "order by p.categoriaInfortunio asc, p.classeEta asc, coalesce(sum(p.numInfortuni),0) desc ")
    List<Key2Sum> sumByCategoriaAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaInfortunio as key1, p.regione as key2, coalesce(sum(p.numInfortuni),0) as totale " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaInfortunio, p.regione " +
            "order by p.categoriaInfortunio asc, p.regione asc, coalesce(sum(p.numInfortuni),0) desc ")
    List<Key2Sum> sumByCategoriaAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select coalesce(sum(p.numInfortuni), 0) " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.categoriaInfortunio = 'MO' " +
            "and (:regione is null or p.regione = :regione) " +
            "and (:sesso is null or p.sesso = :sesso) " +
            "and (:classeEta is null or p.classeEta = :classeEta) ")
    Long sumInfortuniMortali(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("regione") String regione, @Param("sesso") String sesso, @Param("classeEta") Integer classeEta);

    @Query("select coalesce(sum(p.numInfortuni), 0) " +
            "from InfortuniInail p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.categoriaInfortunio in ('MT', 'GP')" +
            "and (:regione is null or p.regione = :regione) " +
            "and (:sesso is null or p.sesso = :sesso) " +
            "and (:classeEta is null or p.classeEta = :classeEta) ")
    Long sumInfortuniMedioGrave(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("regione") String regione, @Param("sesso") String sesso, @Param("classeEta") Integer classeEta);
}
