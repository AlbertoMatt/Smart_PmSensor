package it.synclab.pmsensor.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.synclab.pmsensor.model.AmbientInfos;

@Repository
public interface AmbientInfosRepository extends JpaRepository<AmbientInfos,Long> {
    
    @Query("select a from AmbientInfos a order by id")
    public List<AmbientInfos> getAllAmbientInfos();

    public AmbientInfos getAmbientInfosById(Long id);

    @Query("select a.date from AmbientInfos a where a.id = ?1")
    public Date getDateById(Long id);

    @Query("select a.pm2_5 from AmbientInfos a where a.id = ?1")
    public Double getPm2_5ById(Long id);

    @Query("select a.pm10 from AmbientInfos a where a.id = ?1")
    public Double getPm10ById(Long id);

    @Query("select a.temperature from AmbientInfos a where a.id = ?1")
    public Double getTemperatureById(Long id);

    @Query("select a.umidity from AmbientInfos a where a.id = ?1")
    public Double getUmidityById(Long id);

    @Query("select MAX(a.date) from AmbientInfos a")
    public Date getMaxDate();

    public void deleteById(Long id);

}