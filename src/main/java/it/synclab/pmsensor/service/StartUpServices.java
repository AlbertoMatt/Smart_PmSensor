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
import it.synclab.pmsensor.repository.AmbientInfosRepository;

public class StartUpServices {
    @Value("${sensor.ambienting.url}")
    private String sensorDataUrl;

    @Autowired
    private AmbientInfosService ais;

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
                // 30-10-2022 17:00:00
                ai1.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(completeDate));
                ai1.setPm2_5(Double.valueOf(parts[2]));
                ai1.setPm10(Double.valueOf(parts[3]));
                ai1.setTemperature(Double.valueOf(parts[4]));
                ai1.setUmidity(Double.valueOf(parts[5]));
                list.add(ai1);
            }
            ambientInfosList.setAil(list);
        } catch (Exception e) {
            logger.error("StartUpServices - Error", e);
        }
        logger.debug("StartUpServices END readSensorData");
        return ambientInfosList;
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
