package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	"com.bank.api",
	"com.bank.component",
	"com.bank.openbank"
})
public class BankApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class,args);
	}

}
