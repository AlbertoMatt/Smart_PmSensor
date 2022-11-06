package it.synclab.pmsensor.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.synclab.pmsensor.model.AmbientInfos;
import it.synclab.pmsensor.repository.AmbientInfosRepository;

@Service
public class AmbientInfosService {

    @Autowired
    private AmbientInfosRepository ambInfosRep;

    public List<AmbientInfos> getAllAmbientInfos() {
        return ambInfosRep.getAllAmbientInfos();
    }

    public AmbientInfos getAmbientInfosById(Long id) {
        return ambInfosRep.getAmbientInfosById(id);
    }

    public Date getDateById(Long id) {
        return ambInfosRep.getDateById(id);
    }

    public Double getPm2_5ById(Long id) {
        return ambInfosRep.getPm2_5ById(id);
    }

    public Double getPm10ById(Long id) {
        return ambInfosRep.getPm10ById(id);
    }

    public Double getTemperatureById(Long id) {
        return ambInfosRep.getTemperatureById(id);
    }

    public Double getUmidityById(Long id) {
        return ambInfosRep.getUmidityById(id);
    }

    public Date getMaxDate() {
        return ambInfosRep.getMaxDate();
    }

    public void deleteAmbientInfosById(Long id) {
        ambInfosRep.deleteById(id);
    }

}
