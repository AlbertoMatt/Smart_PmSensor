// package it.synclab.pmsensor.service;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import it.synclab.pmsensor.model.AmbientInfos;
// import it.synclab.pmsensor.model.Temperature;
// import it.synclab.pmsensor.repository.TemperatureRepository;

// @Service
// public class TemperatureService {
//     @Value("${sensor.ambienting.url}")
//     private String sensorDataUrl;

//     @Autowired
//     private TemperatureRepository tempRep;

//     private static final Logger logger = LogManager.getLogger(TemperatureService.class);

//     public Temperature buildTemperatureFromAmbientInfos(AmbientInfos ai) {
//         Temperature temp = new Temperature();
//         temp.setAddress("Padova Galleria Spagna");
//         temp.setLatitude(String.valueOf(45.388653));
//         temp.setLongitude(String.valueOf(11.928344));
//         temp.setTimestamp(ai.getDate());
//         temp.setValue(String.valueOf(ai.getTemperature()));
//         temp.setAmbient_info(ai);
//         return temp;
//     }

//     public void saveTemperatureData(Temperature temp) {
//         logger.debug("TemperatureService START saveTemperatureData");
//         try {
//             tempRep.save(temp);
//         } catch (Exception e) {
//             logger.error("TemperatureService - Error", e);
//         }
//         logger.debug("TemperatureService END saveTemperatureData");
//     }



// }
