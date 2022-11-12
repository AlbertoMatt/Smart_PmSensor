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

import it.synclab.pmsensor.model.ParticularMatter10;
import it.synclab.pmsensor.service.PM10Service;

@RestController
@RequestMapping("/pm10")
public class PM10Controller {
    private static final Logger logger = LogManager.getLogger(PM10Controller.class);

    @Autowired
    private PM10Service pm10Serv;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> getAllPM10() {
        logger.info("PM10Controller - START getAllPM10");
        List<ParticularMatter10> pm10;
        // Security user check
        try {
            pm10 = pm10Serv.getAllParticularMatter10();
        } catch (Exception e) {
            logger.error("PM10Controller -  error - getAllPM10", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END getAllPM10");
        return ResponseEntity.status(HttpStatus.OK).body(pm10);
    }

    @GetMapping("/data-from-ambInf/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> getPM10ByAmbInfId(@PathVariable Long ambientInfo) {
        logger.info("PM10Controller - START getPM10ByAmbInfId");
        ParticularMatter10 pm10;
        // Security user check
        try {
            pm10 = pm10Serv.getParticularMatter10ByAmbInfId(ambientInfo);
        } catch (Exception e) {
            logger.error("PM10Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END getPM10ByAmbInfId");
        return ResponseEntity.status(HttpStatus.OK).body(pm10);
    }

    @GetMapping("/value/{id}")
    @ResponseBody
    public ResponseEntity<Object> getPM10ValueById(@PathVariable Long id) {
        logger.info("PM10Controller - START getPM10ValueById");
        String value;
        // Security user check
        try {
            value = pm10Serv.getValueById(id);
        } catch (Exception e) {
            logger.error("PM10Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END getPM10ValueById");
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    @PutMapping("/update/value/{id}")
    @ResponseBody
    public ResponseEntity<Object> updatePM10ValueById(@PathVariable Long id, @RequestBody String value) {
        logger.info("PM10Controller - START updatePM10ValueById");
        // Security user check
        try {
            pm10Serv.updatePM10ValueById(value, id);
        } catch (Exception e) {
            logger.error("PM10Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END updatePM10ValueById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/value/ambientInfo/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> updatePM10ValueByAIId(@PathVariable Long ambientInfo, @RequestBody String value) {
        logger.info("PM10Controller - START updatePM10ValueByAIId");
        // Security user check
        try {
            pm10Serv.updatePM10ValueByAIId(value, ambientInfo);
        } catch (Exception e) {
            logger.error("PM10Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END updatePM10ValueByAIId");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/ambientInfo/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> deletePM10ByAIId(@PathVariable Long ambientInfo) {
        logger.info("PM10Controller - START deletePM10ByAIId");
        // Security user check
        try {
            pm10Serv.deletePM10ByAIId(ambientInfo);
        } catch (Exception e) {
            logger.error("PM10Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END deletePM10ByAIId");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deletePM10ById(@PathVariable Long id) {
        logger.info("PM10Controller - START deletePM10ById");
        // Security user check
        try {
            pm10Serv.deletePM10ById(id);
        } catch (Exception e) {
            logger.error("PM10Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM10Controller - END deletePM10ById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
