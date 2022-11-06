package it.synclab.pmsensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import it.synclab.pmsensor.service.StartUpServices;

@SpringBootApplication
@ComponentScan(basePackages = {"it.synclab"})
@EnableScheduling
public class SmartPmSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartPmSensorApplication.class, args);
	}

	@Bean
	public StartUpServices createStartUpServices() {
		return new StartUpServices();
	}

}
