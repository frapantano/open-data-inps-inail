package it.university.opendata.integration_backend.inps.repo;

import it.university.opendata.integration_backend.inps.entity.PensioniInps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PensioniInpsRepository extends JpaRepository<PensioniInps, Long> {
}
