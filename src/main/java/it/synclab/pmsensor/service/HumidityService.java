// package it.synclab.pmsensor.service;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import it.synclab.pmsensor.model.AmbientInfos;
// import it.synclab.pmsensor.model.Humidity;
// import it.synclab.pmsensor.repository.HumidityRepository;

// @Service
// public class HumidityService {

//     @Value("${sensor.ambienting.url}")
//     private String sensorDataUrl;

//     @Autowired
//     private HumidityRepository humRep;

//     private static final Logger logger = LogManager.getLogger(HumidityService.class);

//     public Humidity buildHumidityFromAmbientInfos(AmbientInfos ai) {
//         Humidity hum = new Humidity();
//         hum.setAddress("Padova Galleria Spagna");
//         hum.setLatitude(String.valueOf(45.388653));
//         hum.setLongitude(String.valueOf(11.928344));
//         hum.setTimestamp(ai.getDate());
//         hum.setValue(String.valueOf(ai.getUmidity()));
//         hum.setAmbient_info(ai);
//         return hum;
//     }

//     public void saveHumidityData(Humidity humidity) {
//         logger.debug("HumidityService START saveHumidityData");
//         try {
//             humRep.save(humidity);
//         } catch (Exception e) {
//             logger.error("HumidityService - Error", e);
//         }
//         logger.debug("HumidityService END saveHumidityData");
//     }

// }
