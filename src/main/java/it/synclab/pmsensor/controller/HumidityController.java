package it.synclab.pmsensor.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.synclab.pmsensor.model.Humidity;
import it.synclab.pmsensor.service.HumidityService;

@RestController
@RequestMapping("/humidities")
public class HumidityController {
      
    private static final Logger logger = LogManager.getLogger(HumidityController.class);

    @Autowired
    private HumidityService humServ;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> getAllHumidities() {
        logger.info("HumidityController - START getAllHumidities");
        List<Humidity> humidities;
        // Security user check
        try {
            humidities = humServ.getAllHumidities();
        } catch (Exception e) {
            logger.error("HumidityController -  error - getAllHumidities", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END getAllHumidities");
        return ResponseEntity.status(HttpStatus.OK).body(humidities);
    }

    @GetMapping("/data-from-ambInf/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> getHumidityByAmbInfId(@PathVariable Long ambientInfo) {
        logger.info("HumidityController - START getHumidityByAmbInfId");
        Humidity h;
        // Security user check
        try {
            h = humServ.getHumidityByAmbInfId(ambientInfo);
        } catch (Exception e) {
            logger.error("HumidityController -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END getHumidityByAmbInfId");
        return ResponseEntity.status(HttpStatus.OK).body(h);
    }

    @GetMapping("/value/{id}")
    @ResponseBody
    public ResponseEntity<Object> getHumidityValueById(@PathVariable Long id) {
        logger.info("HumidityController - START getHumidityValueById");
        String value;
        // Security user check
        try {
            value = humServ.getValueById(id);
        } catch (Exception e) {
            logger.error("HumidityController -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END getHumidityValueById");
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    @PutMapping("/update/value/{id}")
    @ResponseBody
    public ResponseEntity<Object> updateHumidityValueById(@PathVariable Long id, @RequestBody String value) {
        logger.info("HumidityController - START updateHumidityValueById");
        // Security user check
        try {
            humServ.updateHumidityValueById(value, id);
        } catch (Exception e) {
            logger.error("HumidityController -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END updateHumidityValueById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/value/ambientInfo/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> updateHumValueByAIId(@PathVariable Long ambientInfo, @RequestBody String value) {
        logger.info("HumidityController - START updateHumValueByAIId");
        // Security user check
        try {
            humServ.updateHumValueByAIId(value, ambientInfo);
        } catch (Exception e) {
            logger.error("HumidityController -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END updateHumValueByAIId");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/ambientInfo/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> deleteHumidityByAIId(@PathVariable Long ambientInfo) {
        logger.info("HumidityController - START deleteHumidityByAIId");
        // Security user check
        try {
            humServ.deleteHumidityByAIId(ambientInfo);
        } catch (Exception e) {
            logger.error("HumidityController -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END deleteHumidityByAIId");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteHumidityById(@PathVariable Long id) {
        logger.info("HumidityController - START deleteHumidityById");
        // Security user check
        try {
            humServ.deleteHumidityById(id);
        } catch (Exception e) {
            logger.error("HumidityController -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("HumidityController - END deleteHumidityById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
