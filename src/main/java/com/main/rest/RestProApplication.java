package com.main.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RestProApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestProApplication.class, args);
	}

}
