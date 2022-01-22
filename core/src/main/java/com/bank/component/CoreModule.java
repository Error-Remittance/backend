package com.bank.component;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
@Component("com.bank.component")
@EntityScan(basePackages = {
	"com.bank.component.account.domain",
	"com.bank.component.user.domain",
	"com.bank.component.transaction.domain"
})
@EnableJpaRepositories(basePackages = {
	"com.bank.component.account.repository",
	"com.bank.component.user.repository",
	"com.bank.component.transaction.repository"
})
public class CoreModule {


	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}

}
