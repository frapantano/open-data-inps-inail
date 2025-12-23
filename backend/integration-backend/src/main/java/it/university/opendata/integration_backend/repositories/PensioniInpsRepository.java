package it.university.opendata.integration_backend.repositories;

import it.university.opendata.integration_backend.entity.PensioniInps;
import it.university.opendata.integration_backend.util.proiezioni.Key2Sum;
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
            "order by coalesce(sum(p.numPensioni),0) desc, p.sesso asc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.classeEta as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.classeEta " +
            "order by coalesce(sum(p.numPensioni),0) desc, p.classeEta asc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaPensione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaPensione " +
            "order by coalesce(sum(p.numPensioni),0) desc, p.categoriaPensione asc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndCategoriaPensione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.regione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.regione " +
            "order by coalesce(sum(p.numPensioni),0) desc, p.regione asc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaPensione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.sesso = :sesso " +
            "group by p.categoriaPensione " +
            "order by coalesce(sum(p.numPensioni),0) desc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("sesso") String sesso);

    @Query("select p.categoriaPensione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.classeEta = :classeEta " +
            "group by p.categoriaPensione " +
            "order by coalesce(sum(p.numPensioni),0) desc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("classeEta") Integer classeEta);

    @Query("select p.categoriaPensione as key, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.regione = :regione " +
            "group by p.categoriaPensione " +
            "order by coalesce(sum(p.numPensioni),0) desc ")
    List<KeySum> sumNumPensioniByAnnoAndTrimestreAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("regione") String regione);

    @Query("select p.categoriaPensione as key1, p.sesso as key2, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaPensione, p.sesso " +
            "order by p.categoriaPensione asc, p.sesso asc, coalesce(sum(p.numPensioni),0) desc ")
    List<Key2Sum> sumByCategoriaAndSesso(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaPensione as key1, p.classeEta as key2, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaPensione, p.classeEta " +
            "order by p.categoriaPensione asc, p.classeEta asc, coalesce(sum(p.numPensioni),0) desc ")
    List<Key2Sum> sumByCategoriaAndClasseEta(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select p.categoriaPensione as key1, p.regione as key2, coalesce(sum(p.numPensioni),0) as totale " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre " +
            "group by p.categoriaPensione, p.regione " +
            "order by p.categoriaPensione asc, p.regione asc, coalesce(sum(p.numPensioni),0) desc ")
    List<Key2Sum> sumByCategoriaAndRegione(@Param("anno") Integer anno, @Param("trimestre") String trimestre);

    @Query("select coalesce(sum(p.numPensioni), 0) " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.categoriaPensione = 'S'" +
            "and (:regione is null or p.regione = :regione) " +
            "and (:sesso is null or p.sesso = :sesso) " +
            "and (:classeEta is null or p.classeEta = :classeEta) ")
    Long sumPensioniSuperstiti(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("regione") String regione, @Param("sesso") String sesso, @Param("classeEta") Integer classeEta);

    @Query("select coalesce(sum(p.numPensioni), 0) " +
            "from PensioniInps p " +
            "where p.anno = :anno and p.trimestre = :trimestre and p.categoriaPensione = 'I'" +
            "and (:regione is null or p.regione = :regione) " +
            "and (:sesso is null or p.sesso = :sesso) " +
            "and (:classeEta is null or p.classeEta = :classeEta) ")
    Long sumPensioniInvalidita(@Param("anno") Integer anno, @Param("trimestre") String trimestre, @Param("regione") String regione, @Param("sesso") String sesso, @Param("classeEta") Integer classeEta);
}
