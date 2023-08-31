package com.prueba.CRM.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@Slf4j
@EnableJpaRepositories(basePackages = {"com.prueba.CRM"})
@EntityScan(basePackages = {"com.prueba.CRM"})
@ComponentScan(basePackages = {"com.prueba.CRM"})
@SpringBootApplication(scanBasePackages = {"com.prueba.CRM"}, exclude = {})
public class CrmApplication {

	public static void main(String[] args) {
		try {
			TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));

			ConfigurableApplicationContext context = SpringApplication.run(CrmApplication.class, args);
			log.info("=================================================================");
			log.info("Application Started...");
			log.info("=================================================================");
		} catch (Exception ex) {
			log.error("Error al iniciar la aplicación", ex);
		}
	}

}
