package it.synclab.pmsensor.config.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.synclab.pmsensor.config.datasource.PostgreClient;

@Configuration
public class ConfigContext {

    @Bean
    public PostgreClient createPostgresClient() {
        return new PostgreClient();
    }

}
