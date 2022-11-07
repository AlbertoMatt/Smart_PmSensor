package it.synclab.pmsensor.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import it.synclab.pmsensor.model.AmbientInfos;
import it.synclab.pmsensor.model.AmbientInfosList;
import it.synclab.pmsensor.model.Humidity;
import it.synclab.pmsensor.model.ParticularMatter10;
import it.synclab.pmsensor.model.ParticularMatter25;
import it.synclab.pmsensor.model.Temperature;
import it.synclab.pmsensor.repository.AmbientInfosRepository;
import it.synclab.pmsensor.repository.HumidityRepository;

public class StartUpServices {
    @Value("${sensor.ambienting.url}")
    private String sensorDataUrl;

    @Autowired
    private AmbientInfosService ais;

    @Autowired
    private HumidityRepository humidityRepository;

    @Autowired
    private AmbientInfosRepository air;

    private static final Logger logger = LogManager.getLogger(StartUpServices.class);

    private void saveLastData(AmbientInfosList sensors) {
        List<AmbientInfos> newData = new ArrayList<>();
        Date maxDate = ais.getMaxDate();
        for (AmbientInfos row : sensors.getAil()) {
            if (maxDate == null || row.getDate().after(maxDate)) {
                newData.add(row);
            }
        }
        air.saveAll(newData);
    }

    public AmbientInfosList readDataFromSources() throws Exception {
        logger.debug("StartUpServices START readSensorData");
        AmbientInfosList ambientInfosList = new AmbientInfosList();
        List<AmbientInfos> list = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(sensorDataUrl).build();
            Response response = client.newCall(request).execute();
            String txtSensorData = response.body().string();
            String[] rows = txtSensorData.split("\n");
            for (String row : rows) {
                AmbientInfos ai1 = new AmbientInfos();
                String[] parts = row.split(";");
                String completeDate = parts[0] + " " + parts[1] + ":00:00";
                Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(completeDate);
                // 30-10-2022 17:00:00
                ai1.setDate(date);
                ParticularMatter25 pm25= createPm25(parts, date);
                ParticularMatter10 pm10= createPm10(parts, date);
                Temperature temperature= createTemperature(parts, date);
                Humidity humidity = createHumidity(parts, date);


                // humidity = humidityRepository.save(humidity);
                ai1.setPMatter25(pm25);
                ai1.setPMatter10(pm10);
                ai1.setTemp(temperature);
                ai1.setHumidity(humidity);

                list.add(ai1);
            }
            ambientInfosList.setAil(list);
        } catch (Exception e) {
            logger.error("StartUpServices - Error", e);
        }
        logger.debug("StartUpServices END readSensorData");
        return ambientInfosList;
    }

    private ParticularMatter25 createPm25(String[] parts, Date date) {
        ParticularMatter25 pm25 = new ParticularMatter25();
        pm25.setAddress("Padova Galleria Spagna");
        pm25.setLatitude("45.388653");
        pm25.setLongitude("11.928344");
        pm25.setTimestamp(date);
        pm25.setValue(parts[2]);
        return pm25;
    }
    
    private ParticularMatter10 createPm10(String[] parts, Date date) {
        ParticularMatter10 pm10 = new ParticularMatter10();
        pm10.setAddress("Padova Galleria Spagna");
        pm10.setLatitude("45.388653");
        pm10.setLongitude("11.928344");
        pm10.setTimestamp(date);
        pm10.setValue(parts[3]);
        return pm10;
    }
    
    private Temperature createTemperature(String[] parts, Date date) {
        Temperature temperature = new Temperature();
        temperature.setAddress("Padova Galleria Spagna");
        temperature.setLatitude("45.388653");
        temperature.setLongitude("11.928344");
        temperature.setTimestamp(date);
        temperature.setValue(parts[4]);
        return temperature;
    }
    
    private Humidity createHumidity(String[] parts, Date date) {
        Humidity humidity = new Humidity();
        humidity.setAddress("Padova Galleria Spagna");
        humidity.setLatitude("45.388653");
        humidity.setLongitude("11.928344");
        humidity.setTimestamp(date);
        humidity.setValue(parts[5]);
        return humidity;
    }

    @Scheduled(cron = "${polling.timer}")
    public void updateSensorsData() {
        logger.debug("StartUpServices START updateSensorsData");
        try {
            AmbientInfosList sensors = readDataFromSources();
            saveLastData(sensors);

        } catch (Exception e) {
            logger.error("StartUpServices ERROR updateSensorsData", e);
        }
        logger.debug("StartUpServices END updateSensorsData");
    }

}
