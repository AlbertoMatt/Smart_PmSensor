package it.synclab.pmsensor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.synclab.pmsensor.model.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

    @Query("select t from Temperature t order by id")
    public List<Temperature> getAllTemperature();

    @Query("select t from Temperature t where t.ambientInfo.id = ?1")
    public Temperature getTemperatureByAmbInfId(Long ambientInfo);

    @Query("select t.value from Temperature t where t.id = ?1")
    public String getValueById(Long id);

    @Transactional
    @Modifying
    @Query("update Temperature t set t.value = ?1 where t.id = ?2")
    public void updateTemperatureValueById(String value, Long id);

    @Transactional
    @Modifying
    @Query("update Temperature t set t.value = ?1 where t.ambientInfo.id = ?2")
    public void updateTempValueByAIId(String value, Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete Temperature t where t.ambientInfo.id = ?1")
    public void deleteTemperatureByAIId(Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete Temperature t where t.id = ?1")
    public void deleteTemperatureById(Long id);

}
