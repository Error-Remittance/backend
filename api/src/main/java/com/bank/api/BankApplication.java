package com.bank.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {
	"com.bank.api",
	"com.bank.component"
})
public class BankApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class,args);
	}

}
