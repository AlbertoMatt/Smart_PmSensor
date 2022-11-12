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

import it.synclab.pmsensor.model.Temperature;
import it.synclab.pmsensor.service.TemperatureService;

@RestController
@RequestMapping("/temperatures")
public class TemperatureController {
     private static final Logger logger = LogManager.getLogger(TemperatureController.class);
     
     @Autowired
     private TemperatureService tempServ;

     @GetMapping("/all")
     @ResponseBody
     public ResponseEntity<Object> getAllTemperatures() {
         logger.info("TemperatureController - START getAllTemperatures");
         List<Temperature> temperatures;
         // Security user check
         try {
             temperatures = tempServ.getAllTemperatures();
         } catch (Exception e) {
             logger.error("TemperatureController -  error - getAllTemperatures", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("TemperatureController - END getAllTemperatures");
         return ResponseEntity.status(HttpStatus.OK).body(temperatures);
     }

     @GetMapping("/data-from-ambInf/{ambientInfo}")
     @ResponseBody
     public ResponseEntity<Object> getTemperatureByAmbInfId(@PathVariable Long ambientInfo) {
         logger.info("TemperatureController - START getTemperatureByAmbInfId");
         Temperature t;
         // Security user check
         try {
             t = tempServ.getTemperatureByAmbInfId(ambientInfo);
         } catch (Exception e) {
             logger.error("TemperatureController -  error", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("TemperatureController - END getTemperatureByAmbInfId");
         return ResponseEntity.status(HttpStatus.OK).body(t);
     }

     @GetMapping("/value/{id}")
     @ResponseBody
     public ResponseEntity<Object> getTemperatureValueById(@PathVariable Long id) {
         logger.info("TemperatureController - START getTemperatureValueById");
         String value;
         // Security user check
         try {
             value = tempServ.getValueById(id);
         } catch (Exception e) {
             logger.error("TemperatureController -  error", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("TemperatureController - END getTemperatureValueById");
         return ResponseEntity.status(HttpStatus.OK).body(value);
     }

     @PutMapping("/update/value/{id}")
     @ResponseBody
     public ResponseEntity<Object> updateTemperatureValueById(@PathVariable Long id, @RequestBody String value) {
         logger.info("TemperatureController - START updateTemperatureValueById");
         // Security user check
         try {
             tempServ.updateTemperatureValueById(value, id);
         } catch (Exception e) {
             logger.error("TemperatureController -  error", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("TemperatureController - END updateTemperatureValueById");
         return ResponseEntity.status(HttpStatus.OK).build();
     }

     @PutMapping("/update/value/ambientInfo/{ambientInfo}")
     @ResponseBody
     public ResponseEntity<Object> updateTempValueByAIId(@PathVariable Long ambientInfo, @RequestBody String value) {
         logger.info("TemperatureController - START updateTempValueByAIId");
         // Security user check
         try {
             tempServ.updateTempValueByAIId(value, ambientInfo);
         } catch (Exception e) {
             logger.error("TemperatureController -  error", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("TemperatureController - END updateTempValueByAIId");
         return ResponseEntity.status(HttpStatus.OK).build();
     }

     @DeleteMapping("/delete/ambientInfo/{ambientInfo}")
     @ResponseBody
     public ResponseEntity<Object> deleteTemperatureByAIId(@PathVariable Long ambientInfo) {
         logger.info("TemperatureController - START deleteTemperatureByAIId");
         // Security user check
         try {
             tempServ.deleteTemperatureByAIId(ambientInfo);
         } catch (Exception e) {
             logger.error("TemperatureController -  error", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("TemperatureController - END deleteTemperatureByAIId");
         return ResponseEntity.status(HttpStatus.OK).build();
     }

     @DeleteMapping("/delete/{id}")
     @ResponseBody
     public ResponseEntity<Object> deleteTemperatureById(@PathVariable Long id) {
         logger.info("HumidityController - START deleteTemperatureById");
         // Security user check
         try {
             tempServ.deleteTemperatureById(id);
         } catch (Exception e) {
             logger.error("HumidityController -  error", e);
             return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
         }
         logger.info("HumidityController - END deleteTemperatureById");
         return ResponseEntity.status(HttpStatus.OK).build();
     }





}
