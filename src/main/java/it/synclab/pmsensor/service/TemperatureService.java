package it.synclab.pmsensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.synclab.pmsensor.model.Temperature;
import it.synclab.pmsensor.repository.TemperatureRepository;

@Service
public class TemperatureService {

    @Autowired
    private TemperatureRepository tempRep;

    public List<Temperature> getAllTemperatures() {
        List<Temperature> temperatures = tempRep.getAllTemperature();
        return temperatures;
    }

    public Temperature getTemperatureByAmbInfId(Long ambientInfo) {
        Temperature t = tempRep.getTemperatureByAmbInfId(ambientInfo);
        return t;
    }

    public String getValueById(Long id) {
        return tempRep.getValueById(id);
    }

    public void updateTemperatureValueById(String value, Long id) {
        tempRep.updateTemperatureValueById(value, id);
    }

    public void updateTempValueByAIId(String value, Long ambientInfo) {
        tempRep.updateTempValueByAIId(value, ambientInfo);
    }

    public void deleteTemperatureByAIId(Long ambientInfo) {
        tempRep.deleteTemperatureByAIId(ambientInfo);
    }

    public void deleteTemperatureById(Long id) {
        tempRep.deleteTemperatureById(id);
    }



}
