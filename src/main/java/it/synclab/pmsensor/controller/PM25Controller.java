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

import it.synclab.pmsensor.model.ParticularMatter25;
import it.synclab.pmsensor.service.PM25Service;

@RestController
@RequestMapping("/pm25")
public class PM25Controller {
     private static final Logger logger = LogManager.getLogger(PM25Controller.class);

    @Autowired
    private PM25Service pm25Serv;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Object> getAllPM25() {
        logger.info("PM25Controller - START getAllPM25");
        List<ParticularMatter25> pm25;
        // Security user check
        try {
            pm25 = pm25Serv.getAllParticularMatter25();
        } catch (Exception e) {
            logger.error("PM25Controller -  error - getAllPM25", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END getAllPM25");
        return ResponseEntity.status(HttpStatus.OK).body(pm25);
    }

    @GetMapping("/data-from-ambInf/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> getPM25ByAmbInfId(@PathVariable Long ambientInfo) {
        logger.info("PM25Controller - START getPM25ByAmbInfId");
        ParticularMatter25 pm25;
        // Security user check
        try {
            pm25 = pm25Serv.getParticularMatter25ByAmbInfId(ambientInfo);
        } catch (Exception e) {
            logger.error("PM25Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END getPM25ByAmbInfId");
        return ResponseEntity.status(HttpStatus.OK).body(pm25);
    }

    @GetMapping("/value/{id}")
    @ResponseBody
    public ResponseEntity<Object> getPM25ValueById(@PathVariable Long id) {
        logger.info("PM25Controller - START getPM25ValueById");
        String value;
        // Security user check
        try {
            value = pm25Serv.getValueById(id);
        } catch (Exception e) {
            logger.error("PM25Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END getPM25ValueById");
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    @PutMapping("/update/value/{id}")
    @ResponseBody
    public ResponseEntity<Object> updatePM25ValueById(@PathVariable Long id, @RequestBody String value) {
        logger.info("PM25Controller - START updatePM25ValueById");
        // Security user check
        try {
            pm25Serv.updatePM25ValueById(value, id);
        } catch (Exception e) {
            logger.error("PM25Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END updatePM25ValueById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/value/ambientInfo/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> updatePM25ValueByAIId(@PathVariable Long ambientInfo, @RequestBody String value) {
        logger.info("PM25Controller - START updatePM25ValueByAIId");
        // Security user check
        try {
            pm25Serv.updatePM25ValueByAIId(value, ambientInfo);
        } catch (Exception e) {
            logger.error("PM25Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END updatePM25ValueByAIId");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/ambientInfo/{ambientInfo}")
    @ResponseBody
    public ResponseEntity<Object> deletePM25ByAIId(@PathVariable Long ambientInfo) {
        logger.info("PM25Controller - START deletePM25ByAIId");
        // Security user check
        try {
            pm25Serv.deletePM25ByAIId(ambientInfo);
        } catch (Exception e) {
            logger.error("PM25Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END deletePM25ByAIId");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deletePM25ById(@PathVariable Long id) {
        logger.info("PM25Controller - START deletePM25ById");
        // Security user check
        try {
            pm25Serv.deletePM25ById(id);
        } catch (Exception e) {
            logger.error("PM25Controller -  error", e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        logger.info("PM25Controller - END deletePM25ById");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
