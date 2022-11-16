package it.synclab.pmsensor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.synclab.pmsensor.model.AmbientInfos;
import it.synclab.pmsensor.model.AmbientInfosList;
import it.synclab.pmsensor.service.AmbientInfosService;
import it.synclab.pmsensor.service.StartUpServices;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ambient-infos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AmbientInfosController {    

    @Autowired
    AmbientInfosService ambientIService;

    @Autowired
    StartUpServices sup;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> getAllAmbientInfos() {
        log.info("AmbientInfosController - START getAllAmbientInfos");
        List<AmbientInfos> amb_inf;
        try {
            amb_inf = ambientIService.getAllAmbientInfos();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error - getAllAmbientInfos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAllAmbientInfos");
        return ResponseEntity.status(HttpStatus.OK).body(amb_inf);
    }

    @GetMapping("/get-data-from-TXT")
    @ResponseBody
    public ResponseEntity<AmbientInfosList> getAIDataFromSource() {
        log.info("SensorResources - START getAIDataFromSource");
        AmbientInfosList list;
        // Security user check
        try {
            list = sup.readDataFromSources();
        } catch (Exception e) {
            log.error("SensorResources -  error - getAIDataFromSource", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("SensorResources - END getAIDataFromSource");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AmbientInfos> getAmbientInfosData(@PathVariable Long id) {
        log.info("AmbientInfosController - START getAmbientInfosData");
        AmbientInfos a;
        // Security user check
        try {
            a = ambientIService.getAmbientInfosById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAmbientInfosData");
        return ResponseEntity.status(HttpStatus.OK).body(a);
    }

    @GetMapping("/date/{id}")
    @ResponseBody
    public ResponseEntity<Object> getAIDateById(@PathVariable Long id) {
        log.info("AmbientInfosController - START getAIDateById");
        Date date;
        // Security user check
        try {
            date = ambientIService.getDateById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAIDateById");
        return ResponseEntity.status(HttpStatus.OK).body(date);
    }

    @GetMapping("/pm2_5/{id}")
    @ResponseBody
    public ResponseEntity<Object> getAIPm2_5ById(@PathVariable Long id) {
        log.info("AmbientInfosController - START getAIPm2_5ById");
        Double pm2_5;
        // Security user check
        try {
            pm2_5 = ambientIService.getPm2_5ById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAIPm2_5ById");
        return ResponseEntity.status(HttpStatus.OK).body(pm2_5);
    }

    @GetMapping("/pm10/{id}")
    @ResponseBody
    public ResponseEntity<Object> getAIPm10ById(@PathVariable Long id) {
        log.info("AmbientInfosController - START getAIPm10ById");
        Double pm10;
        // Security user check
        try {
            pm10 = ambientIService.getPm10ById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAIPm10ById");
        return ResponseEntity.status(HttpStatus.OK).body(pm10);
    }

    @GetMapping("/temperature/{id}")
    @ResponseBody
    public ResponseEntity<Object> getAITemperatureById(@PathVariable Long id) {
        log.info("AmbientInfosController - START getAITemperatureById");
        Double temperature;
        // Security user check
        try {
            temperature = ambientIService.getTemperatureById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAITemperatureById");
        return ResponseEntity.status(HttpStatus.OK).body(temperature);
    }

    @GetMapping("/umidity/{id}")
    @ResponseBody
    public ResponseEntity<Object> getAIUmidityById(@PathVariable Long id) {
        log.info("AmbientInfosController - START getAIUmidityById");
        Double umidity;
        // Security user check
        try {
            umidity = ambientIService.getUmidityById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END getAIUmidityById");
        return ResponseEntity.status(HttpStatus.OK).body(umidity);
    }

    @PostMapping("/save-data")
    @ResponseBody
    public ResponseEntity<Object> saveSensorData() {
        log.info("SensorResources - START saveSensorData");
        // Security user check
        try {
            sup.updateSensorsData();
        } catch (Exception e) {
            log.error("SensorResources -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("SensorResources - END saveSensorData");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAmbientInfosById(@PathVariable Long id) {
        log.info("AmbientInfosController - START deleteAmbientInfosById");
        try {
            ambientIService.deleteAmbientInfosById(id);
        } catch (NullPointerException e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("AmbientInfosController -  error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        log.info("AmbientInfosController - END deleteAmbientInfosById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
