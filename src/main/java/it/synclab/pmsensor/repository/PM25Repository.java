package it.synclab.pmsensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.pmsensor.model.ParticularMatter25;

@Repository
public interface PM25Repository extends JpaRepository<ParticularMatter25, Long> {

}
