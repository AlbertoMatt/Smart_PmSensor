package it.synclab.pmsensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.synclab.pmsensor.model.Humidity;
import it.synclab.pmsensor.repository.HumidityRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HumidityService {

    @Autowired
    private HumidityRepository humRep;


    public List<Humidity> getAllHumidities() {
        log.debug("HumidityService START getAllHumidities");
        List<Humidity> humidities = humRep.getAllHumidity();
        log.debug("HumidityService END getAllHumidities");
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
