package com.bank.api.config.jwt;

public class JwtConstant {
	public static final String SECRET = "secret";
	public static final int EXPIRATION_TIME = 60000 * 10; // 10분
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
}
