package it.synclab.pmsensor.config.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;


public class PostgreClient {

    Properties props = new Properties();
    @Value("${spring.datasource.url}")
    private String jdbcURL;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection conn = null;

    private static final Logger logger = LogManager.getLogger(PostgreClient.class);

    public PostgreClient() {
        logger.info("PostgreClient START - trying connection to PosgresDB");
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/SmartPmSensor", "postgres",
                    "password");
        } catch (SQLException e) {
            logger.error("PostgreClient Error", e);
        }
        logger.info("PostgreClient END - connection to PosgresDB Success");
    }

}
