package it.synclab.pmsensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.pmsensor.model.ParticularMatter10;

@Repository
public interface PM10Repository extends JpaRepository<ParticularMatter10, Long> {

}
