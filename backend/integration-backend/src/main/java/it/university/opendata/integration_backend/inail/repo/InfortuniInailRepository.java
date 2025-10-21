package it.university.opendata.integration_backend.inail.repo;

import it.university.opendata.integration_backend.inail.entity.InfortuniInail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfortuniInailRepository extends JpaRepository<InfortuniInail, Long> {
}
