// package it.synclab.pmsensor.service;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import it.synclab.pmsensor.model.AmbientInfos;
// import it.synclab.pmsensor.model.ParticularMatter25;
// import it.synclab.pmsensor.repository.PM25Repository;

// @Service
// public class PM25Service {

//     @Value("${sensor.ambienting.url}")
//     private String sensorDataUrl;

//     @Autowired
//     private PM25Repository pm25Rep;

//     private static final Logger logger = LogManager.getLogger(PM25Service.class);

//     public ParticularMatter25 buildPm25FromAmbientInfos(AmbientInfos ai) {
//         ParticularMatter25 pMatter25 = new ParticularMatter25();
//         pMatter25.setAddress("Padova Galleria Spagna");
//         pMatter25.setLatitude(String.valueOf(45.388653));
//         pMatter25.setLongitude(String.valueOf(11.928344));
//         pMatter25.setTimestamp(ai.getDate());
//         pMatter25.setValue(String.valueOf(ai.getPm10()));
//         pMatter25.setAmbient_info(ai);
//         return pMatter25;
//     }

//     public void savePM25Data(ParticularMatter25 pm25) {
//         logger.debug("PM25Service START savePM25Data");
//         try {
//             pm25Rep.save(pm25);
//         } catch (Exception e) {
//             logger.error("PM25Service - Error", e);
//         }
//         logger.debug("PM25Service END savePM25Data");
//     }



// }
