package com.bank.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
	private String version;
	private String title;

	@Bean
	public Docket apiV1() {
		version = "V1";
		title = "Swagger Example API " + version;

		Parameter parameterBuilder = new ParameterBuilder()
			.name(HttpHeaders.AUTHORIZATION)
			.description("Access Token")
			.modelRef(new ModelRef("string"))
			.parameterType("header")
			.required(false)
			.build();

		List<Parameter> globalParamters = new ArrayList<>();
		globalParamters.add(parameterBuilder);

		return new Docket(DocumentationType.SWAGGER_2)
			.globalOperationParameters(globalParamters)
			.useDefaultResponseMessages(false)
			.groupName(version)
			.select()
			.apis(RequestHandlerSelectors.basePackage("me.develop_han.loginService.api"))
			// .paths(PathSelectors.ant("/v1/customer/**"))
			.build()
			.apiInfo(apiInfo(title, version));

	}

	// @Bean
	// public Docket apiV2() {
	// 	version = "V2";
	// 	title = "Swagger Example API " + version;
	//
	// 	return new Docket(DocumentationType.SWAGGER_2)
	// 		.useDefaultResponseMessages(false)
	// 		.groupName(version)
	// 		.select()
	// 		.apis(RequestHandlerSelectors.basePackage("com.example.swagger.v2"))
	// 		// .paths(PathSelectors.ant("/v2/customer/**"))
	// 		.build()
	// 		.apiInfo(apiInfo(title, version));
	//
	// }

	private ApiInfo apiInfo(String title, String version) {
		return new ApiInfo(
			title,
			"Swagger로 생성한 API Docs",
			version,
			"www.example.com",
			new Contact("Contact us", "www.example.com", "seunghanhwang0@gmail.com"),
			"Licenses",
			"www.example.com",
			new ArrayList<>());
	}
}
