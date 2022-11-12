package it.synclab.pmsensor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.synclab.pmsensor.model.ParticularMatter25;

@Repository
public interface PM25Repository extends JpaRepository<ParticularMatter25, Long> {

    @Query("select pm25 from ParticularMatter25 pm25 order by id")
    public List<ParticularMatter25> getAllParticularMatter25();

    @Query("select pm25 from ParticularMatter25 pm25 where pm25.ambientInfo.id = ?1")
    public ParticularMatter25 getParticularMatter25ByAmbInfId(Long ambientInfo);

    @Query("select pm25.value from ParticularMatter25 pm25 where pm25.id = ?1")
    public String getValueById(Long id);

    @Transactional
    @Modifying
    @Query("update ParticularMatter25 pm25 set pm25.value = ?1 where id = ?2")
    public void updatePM25ValueById(String value, Long id);

    @Transactional
    @Modifying
    @Query("update ParticularMatter25 pm25 set pm25.value = ?1 where pm25.ambientInfo.id = ?2")
    public void updatePM25ValueByAIId(String value, Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete ParticularMatter25 pm25 where pm25.ambientInfo.id = ?1")
    public void deletePM25ByAIId(Long ambientInfo);

    @Transactional
    @Modifying
    @Query("delete ParticularMatter25 pm25 where pm25.id = ?1")
    public void deletePM25ById(Long id);

}
