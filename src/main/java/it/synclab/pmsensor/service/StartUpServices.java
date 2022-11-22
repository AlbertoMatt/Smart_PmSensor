package it.synclab.pmsensor.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartUpServices {
    @Value("#{'${sensors.ambienting.url}'.split(',')}")
    private List<String> sensorDataUrl;

    @Autowired
    private AmbientInfosService ais;

    @Autowired
    private AmbientInfosRepository air;

    private void saveLastData(AmbientInfosList sensors) {
        List<AmbientInfos> newData = new ArrayList<>();
        log.debug("start request to get maxDate");
        Date maxDate = ais.getMaxDate();
        log.debug("request for maxDate end with result: {}", maxDate);
        log.debug("start request to get minDate");
        Date minDate = ais.getMinDate();
        log.debug("request for minDate end with result: {}", minDate);
        for (AmbientInfos row : sensors.getAil()) {
            if (maxDate == null && minDate == null || row.getDate().after(maxDate) || row.getDate().before(minDate)) {
                newData.add(row);
            }
        }
        log.debug("start saving data {}", newData);
        air.saveAll(newData);
        log.debug("ended save data");
    }

    public AmbientInfosList readDataFromSources() throws Exception {
        log.debug("StartUpServices START readSensorData");
        AmbientInfosList ambientInfosList = new AmbientInfosList();
        List<AmbientInfos> list = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient();
            for (String sensorUrl : sensorDataUrl) {
                log.debug("start creation data for url: {}", sensorUrl);
                log.debug("execute request with url: {}", sensorUrl);
                Request request = new Request.Builder().url(sensorUrl).build();
                Response response = client.newCall(request).execute();
                log.debug("end request with url: {}", sensorUrl);
                String txtSensorData = response.body().string();
                String[] rows = txtSensorData.split("\n");
                for (String row : rows) {
                    AmbientInfos ai1 = new AmbientInfos();
                    String[] parts = row.split(";");
                    if (parts.length == 6) {
                        String completeDate = parts[0] + " " + parts[1] + ":00:00";
                        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(completeDate);
                        // 30-10-2022 17:00:00
                        ai1.setDate(date);
                        ParticularMatter25 pm25 = createPm25(parts, date);
                        ParticularMatter10 pm10 = createPm10(parts, date);
                        Temperature temperature = createTemperature(parts, date);
                        Humidity humidity = createHumidity(parts, date);

                        ai1.setPMatter25(pm25);
                        ai1.setPMatter10(pm10);
                        ai1.setTemp(temperature);
                        ai1.setHumidity(humidity);

                        list.add(ai1);
                    }

                }
                log.debug("end creation data for url: {}", sensorUrl);
            }

            ambientInfosList.setAil(list);
        } catch (Exception e) {
            log.error("StartUpServices - Error", e);
        }
        log.debug("StartUpServices END readSensorData");
        return ambientInfosList;
    }

    private ParticularMatter25 createPm25(String[] parts, Date date) {
        ParticularMatter25 pm25 = new ParticularMatter25();
        pm25.setAddress("Padova Galleria Spagna");
        pm25.setLatitude("45.388653");
        pm25.setLongitude("11.928344");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date timestamp = new Date(ts.getTime());
        pm25.setTimestamp(timestamp);
        pm25.setDate(date);
        pm25.setValue(Double.parseDouble(parts[2]));
        return pm25;
    }

    private ParticularMatter10 createPm10(String[] parts, Date date) {
        ParticularMatter10 pm10 = new ParticularMatter10();
        pm10.setAddress("Padova Galleria Spagna");
        pm10.setLatitude("45.388653");
        pm10.setLongitude("11.928344");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date timestamp = new Date(ts.getTime());
        pm10.setTimestamp(timestamp);
        pm10.setDate(date);
        pm10.setValue(Double.parseDouble(parts[3]));
        return pm10;
    }

    private Temperature createTemperature(String[] parts, Date date) {
        Temperature temperature = new Temperature();
        temperature.setAddress("Padova Galleria Spagna");
        temperature.setLatitude("45.388653");
        temperature.setLongitude("11.928344");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date timestamp = new Date(ts.getTime());
        temperature.setTimestamp(timestamp);
        temperature.setDate(date);
        temperature.setValue(Double.parseDouble(parts[4]));
        return temperature;
    }

    private Humidity createHumidity(String[] parts, Date date) {
        Humidity humidity = new Humidity();
        humidity.setAddress("Padova Galleria Spagna");
        humidity.setLatitude("45.388653");
        humidity.setLongitude("11.928344");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date timestamp = new Date(ts.getTime());
        humidity.setTimestamp(timestamp);
        humidity.setDate(date);
        humidity.setValue(Double.parseDouble(parts[5]));
        return humidity;
    }

    @Scheduled(cron = "${polling.timer}")
    public void updateSensorsData() {
        log.debug("StartUpServices START updateSensorsData");
        try {
            AmbientInfosList sensors = readDataFromSources();
            saveLastData(sensors);

        } catch (Exception e) {
            log.error("StartUpServices ERROR updateSensorsData", e);
        }
        log.debug("StartUpServices END updateSensorsData");
    }

}
