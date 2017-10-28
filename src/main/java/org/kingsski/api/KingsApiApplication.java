package org.kingsski.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
public class KingsApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KingsApiApplication.class, args);
	}

}
