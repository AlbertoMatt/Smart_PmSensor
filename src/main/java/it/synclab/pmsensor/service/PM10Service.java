// package it.synclab.pmsensor.service;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import it.synclab.pmsensor.model.AmbientInfos;
// import it.synclab.pmsensor.model.ParticularMatter10;
// import it.synclab.pmsensor.repository.PM10Repository;

// @Service
// public class PM10Service {
//     @Value("${sensor.ambienting.url}")
//     private String sensorDataUrl;

//     @Autowired
//     private PM10Repository pm10Rep;

//     private static final Logger logger = LogManager.getLogger(PM10Service.class);

//     public ParticularMatter10 buildPm10FromAmbientInfos(AmbientInfos ai) {
//         ParticularMatter10 pMatter10 = new ParticularMatter10();
//         pMatter10.setAddress("Padova Galleria Spagna");
//         pMatter10.setLatitude(String.valueOf(45.388653));
//         pMatter10.setLongitude(String.valueOf(11.928344));
//         pMatter10.setTimestamp(ai.getDate());
//         pMatter10.setValue(String.valueOf(ai.getUmidity()));
//         pMatter10.setAmbient_info(ai);
//         return pMatter10;
//     }

//     public void savePM10Data(ParticularMatter10 pm10) {
//         logger.debug("PM10Service START savePM10Data");
//         try {
//             pm10Rep.save(pm10);
//         } catch (Exception e) {
//             logger.error("PM10Service - Error", e);
//         }
//         logger.debug("PM10Service END savePM10Data");
//     }



// }
