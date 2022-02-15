package com.bank.api.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bank.api.config.jwt.auth.PrincipalDetails;
import com.bank.component.user.domain.User;
import com.bank.component.user.repository.UserRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final UserRepository userRepository;

	public JwtAuthorizationFilter(
		AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	/**
	 * 요청이 접근할 때 만약 header에 트큰을 달고 온다면 통과시켜주면 된다.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		String jwtHeader = request.getHeader(JwtConstant.HEADER_STRING);

		// 만약 Authorzation header가 없거나 Bearer 토큰이 아니라면
		if (jwtHeader == null || !jwtHeader.startsWith(JwtConstant.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		String jwtToken = jwtHeader.replace(JwtConstant.TOKEN_PREFIX, "");

		String username = JWT.require(Algorithm.HMAC512(JwtConstant.SECRET)).build().verify(jwtToken) // 인증 완료된다면
			.getClaim("username").asString(); // username을 반환하라

		// null이 아니라면 -> 서명이 정상적으로 된 것
		if (username != null) {
			User userEntity = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(username)
			);

			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

			// 인증이 완료되었다는 토큰을 만들어서
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				principalDetails, null, principalDetails.getAuthorities());
			// SecurityContextHolder에 넣어주고 통과시켜준다.
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}
}

