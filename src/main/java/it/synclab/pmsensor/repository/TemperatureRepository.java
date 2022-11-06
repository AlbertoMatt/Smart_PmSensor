package it.synclab.pmsensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.pmsensor.model.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

}
