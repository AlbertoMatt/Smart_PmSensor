package it.synclab.pmsensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.synclab.pmsensor.model.ParticularMatter25;
import it.synclab.pmsensor.repository.AmbientInfosRepository;
import it.synclab.pmsensor.repository.PM25Repository;

@Service
public class PM25Service {

    @Autowired
    private PM25Repository pm25Rep;

    @Autowired
    private AmbientInfosRepository ambientInfosRepository;

    public List<ParticularMatter25> getAllParticularMatter25() {
        List<ParticularMatter25> pm25 = pm25Rep.getAllParticularMatter25();
        return pm25;
    }

    public ParticularMatter25 getParticularMatter25ByAmbInfId(Long ambientInfo) {
        ParticularMatter25 pm25 = pm25Rep.getParticularMatter25ByAmbInfId(ambientInfo);
        return pm25;
    }

    public String getValueById(Long id) {
        return pm25Rep.getValueById(id);
    }

    public void updatePM25ValueById(String value, Long id) {
        pm25Rep.updatePM25ValueById(value, id);
    }

    public void updatePM25ValueByAIId(String value, Long ambientInfo) {
        pm25Rep.updatePM25ValueByAIId(value, ambientInfo);
    }

    public void deletePM25ByAIId(Long ambientInfo) {
        pm25Rep.deletePM25ByAIId(ambientInfo);
    }

    public void deletePM25ById(Long id) {
        ambientInfosRepository.updateAmbientInfosPMatter25(id, null);
        pm25Rep.deletePM25ById(id);
    }

}
