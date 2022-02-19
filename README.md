# (백엔드)

착오송금 반환 서비스(2022.01.13 ~ 진행중)

## 프로젝트 구조

![image](https://user-images.githubusercontent.com/71204049/135059863-5a7fe359-b1e4-4f46-ae53-86879bb62306.png)


## ✍Skill

- Java (openJDK11 Local 설정)

- Spring Boot **2.1.7.RELEASE** 

- Gradle **7.1.1**([gradle](https://github.com/brightest-ko/springboot-webservice/tree/master/gradle)/[wrapper](https://github.com/brightest-ko/springboot-webservice/tree/master/gradle/wrapper)/**gradle-wrapper.properties**)

  - settings.grade

    ```
    rootProject.name = 'backend'
    include 'WER-core'
    include 'WER-api'
    ```

- Mysql 8.0.26 (Local 설정)
  - root 
  - ```
    create user ‘bank_admin’@‘%’ identified by 'bank1234’;
    GRANT ALL PRIVILEGES ON *.* TO ‘bank_admin’@‘%’;
    create database bank_backend;
    use security;
    ```

- Tool

  - DB(Mysql)

    - cmd창
    - mysql workbench(Oracle사 제공)

  - API 테스트
  
    - Postman

  - Git https://github.com/Error-Remittance/backend

    - Fork(https://git-fork.com/ 무료버전 제공)
    - SourceTree

    

## ✍Entity (Domain)

- Account
  
  - **Long account_id** id
    
  - String number
    
  - String name
    
  - double balance
    
  - **WooriUser** wooriUser
    
    ```
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "woori_user_id")
    private WooriUser wooriUser;
    ```
  
- User
  - **Long woori_user_id** id

  - String user_id username

  - String password password


    ```
    @OneToOne(mappedBy = "user_seq_no")
    private UserInfo userInfo;
    ```

  

## ✍How To Develop

- Spring Security (JWT Token)

  ```
  package com.wooribank.backend.configuration;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  import org.springframework.security.crypto.password.PasswordEncoder;
  
  @Configuration
  public class SecurityConfiguration {
  
      @Bean
      public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
      }
  }
  ```

- 금융결제원 API 사용 https://developers.kftc.or.kr/dev

예시

  ```
  /**
	 * 잔액조회
	 */
	public InquiryBalanceResponseDto requestBalance(InquiryBalanceRequestDto inquiryBalanceRequestDto) {
		String url = base_url + "/account/balance/fin_num";
		HttpEntity<String> balance = new HttpEntity<>(openBankUtil.makeAccessTokenHeader(accessToken));
		inquiryBalanceRequestDto.setInquiryBalanceRequestDto(
			makeBankTranId(useCode),
			openBankUtil.getTransTime());

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
			.queryParam("bank_tran_id", inquiryBalanceRequestDto.getBank_tran_id())
			.queryParam("fintech_use_num", inquiryBalanceRequestDto.getFintech_use_num())
			.queryParam("tran_dtime", inquiryBalanceRequestDto.getTran_dtime())
			.build();

		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, balance, InquiryBalanceResponseDto.class)
			.getBody();
	}
  ```

- API 문서 자동화 규격 제공 (http://localhost:8080/swagger-ui/index.html)

  ```
  package com.wooribank.backend.configuration;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import springfox.documentation.builders.PathSelectors;
  import springfox.documentation.builders.RequestHandlerSelectors;
  import springfox.documentation.spi.DocumentationType;
  import springfox.documentation.spring.web.plugins.Docket;
  import springfox.documentation.swagger2.annotations.EnableSwagger2;
  
  @Configuration
  public class SwaggerConfiguration {
      @Bean
      public Docket api() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .select()
                  .apis(RequestHandlerSelectors.basePackage("com.wooribank.backend.controller"))
                  .paths(PathSelectors.any())
                  .build();
      }
  }
  ```
