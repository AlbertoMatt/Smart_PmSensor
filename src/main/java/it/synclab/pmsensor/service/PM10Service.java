package it.synclab.pmsensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.synclab.pmsensor.model.ParticularMatter10;
import it.synclab.pmsensor.repository.PM10Repository;

@Service
public class PM10Service {

    @Autowired
    private PM10Repository pm10Rep;

    public List<ParticularMatter10> getAllParticularMatter10() {
        List<ParticularMatter10> pm10 = pm10Rep.getAllParticularMatter10();
        return pm10;
    }

    public ParticularMatter10 getParticularMatter10ByAmbInfId(Long ambientInfo) {
        ParticularMatter10 pm10 = pm10Rep.getParticularMatter10ByAmbInfId(ambientInfo);
        return pm10;
    }

    public String getValueById(Long id) {
        return pm10Rep.getValueById(id);
    }

    public void updatePM10ValueById(String value, Long id) {
        pm10Rep.updatePM10ValueById(value, id);
    }

    public void updatePM10ValueByAIId(String value, Long ambientInfo) {
        pm10Rep.updatePM10ValueByAIId(value, ambientInfo);
    }

    public void deletePM10ByAIId(Long ambientInfo) {
        pm10Rep.deletePM10ByAIId(ambientInfo);
    }

    public void deletePM10ById(Long id) {
        pm10Rep.deletePM10ById(id);
    }



}
