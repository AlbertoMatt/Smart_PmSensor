package it.synclab.pmsensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.pmsensor.model.Humidity;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {

}
