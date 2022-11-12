package it.synclab.pmsensor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.synclab.pmsensor.model.Humidity;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {

    @Query("select h from Humidity h order by id")
    public List<Humidity> getAllHumidity();

    @Query("select h from Humidity h where h.ambientInfo.id = ?1")
    public Humidity getHumidityByAmbInfId(Long ambientInfo);

    
    @Query("select h.value from Humidity h where h.id = ?1")
    public String getValueById(Long id);
    
    @Transactional
    @Modifying
    @Query("update Humidity h set h.value = ?1 where id = ?2")
    public void updateHumidityValueById(String value, Long id);
    
    @Transactional
    @Modifying
    @Query("update Humidity h set h.value = ?1 where h.ambientInfo.id = ?2")
    public void updateHumValueByAIId(String value, Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete Humidity h where h.ambientInfo.id = ?1")
    public void deleteHumidityByAIId(Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete Humidity h where h.id = ?1")
    public void deleteHumidityById(Long id);



}
