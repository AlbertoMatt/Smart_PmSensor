package it.synclab.pmsensor.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.synclab.pmsensor.model.AmbientInfos;
import it.synclab.pmsensor.model.Humidity;
import it.synclab.pmsensor.repository.AmbientInfosRepository;
import it.synclab.pmsensor.repository.HumidityRepository;

@Service
public class HumidityService {

    @Value("${sensor.ambienting.url}")
    private String sensorDataUrl;

    @Autowired
    private HumidityRepository humRep;

    private static final Logger logger = LogManager.getLogger(HumidityService.class);

    public List<Humidity> getAllHumidities() {
        logger.debug("HumidityService START getAllHumidities");
        List<Humidity> humidities = humRep.getAllHumidity();
        logger.debug("HumidityService END getAllHumidities");
        return humidities;
    }

    public Humidity getHumidityByAmbInfId(Long ambientInfo){
        Humidity h=humRep.getHumidityByAmbInfId(ambientInfo);
        return h;
    }

    public String getValueById(Long id) {
        return humRep.getValueById(id);
    }

    public void updateHumidityValueById(String value, Long id){
        humRep.updateHumidityValueById(value, id);
    }

    public void updateHumValueByAIId(String value, Long ambientInfo){
        humRep.updateHumValueByAIId(value, ambientInfo);
    }

    public void deleteHumidityByAIId(Long ambientInfo){
        humRep.deleteHumidityByAIId(ambientInfo);
    }

    public void deleteHumidityById(Long id){
        humRep.deleteHumidityById(id);
    }

}
