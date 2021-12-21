package com.ibge.ibge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.ibge.ibge.externo"})
@SpringBootApplication(scanBasePackages = { "com.ibge.ibge" })
public class IbgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbgeApplication.class, args);
	}

}
