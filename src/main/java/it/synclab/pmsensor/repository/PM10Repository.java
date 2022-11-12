package it.synclab.pmsensor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.synclab.pmsensor.model.ParticularMatter10;

@Repository
public interface PM10Repository extends JpaRepository<ParticularMatter10, Long> {

    @Query("select pm10 from ParticularMatter10 pm10 order by id")
    public List<ParticularMatter10> getAllParticularMatter10();

    @Query("select pm10 from ParticularMatter10 pm10 where pm10.ambientInfo.id = ?1")
    public ParticularMatter10 getParticularMatter10ByAmbInfId(Long ambientInfo);

    @Query("select pm10.value from ParticularMatter10 pm10 where pm10.id = ?1")
    public String getValueById(Long id);


    @Transactional
    @Modifying
    @Query("update ParticularMatter10 pm10 set pm10.value = ?1 where id = ?2")
    public void updatePM10ValueById(String value, Long id);

    @Transactional
    @Modifying
    @Query("update ParticularMatter10 pm10 set pm10.value = ?1 where pm10.ambientInfo.id = ?2")
    public void updatePM10ValueByAIId(String value, Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete ParticularMatter10 pm10 where pm10.ambientInfo.id = ?1")
    public void deletePM10ByAIId(Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete ParticularMatter10 pm10 where pm10.id = ?1")
    public void deletePM10ById(Long id);

}
