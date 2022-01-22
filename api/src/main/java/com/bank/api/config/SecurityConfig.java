package com.bank.api.config;

import static org.springframework.http.HttpMethod.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// @EnableWebSecurity
public class SecurityConfig {

	// private final UserDetailsService userDetailsService;
	// private final BCryptPasswordEncoder bCryptPasswordEncoder;
	//
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// 	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	// }
	//
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// 	CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
	// 		authenticationManagerBean());
	// 	customAuthenticationFilter.setFilterProcessesUrl("/api/login");
	// 	CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter();
	// 	http
	// 		.csrf().disable()
	// 		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	// 	http.authorizeRequests().antMatchers("/exception/**","/item/**", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
	// 		.permitAll();
	// 	http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
	// 	http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
	// 	http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
	// 	http.authorizeRequests().anyRequest().authenticated();
	// 	http.addFilter(customAuthenticationFilter);
	// 	http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // 모든 필터 전에 실행하고 싶음
	// }
	//
	// @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// 	return super.authenticationManagerBean();
	// }
}
