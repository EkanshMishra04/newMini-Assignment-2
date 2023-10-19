package com.nagarro.javaMiniAssignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JavaMiniAssignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaMiniAssignment2Application.class, args);
	}
}