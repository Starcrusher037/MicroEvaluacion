package com.duoc.learningplatform.evaluacion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EvaluacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluacionServiceApplication.class, args);
	}

}
