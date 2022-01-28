package com.openapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class OpenBankUtil {
	/**
	 * 은행 거래 고유번호 랜덤 생성
	 */

	public String getRandomNumber(String useCode) {

		Random rand = new Random();
		String rst = Integer.toString(rand.nextInt(8) + 1);
		for (int i = 0; i < 8; i++) {
			rst += Integer.toString(rand.nextInt(9));
		}
		return useCode +"U" +  rst;
	}

	/**
	 * 거래시간
	 */
	public String getTransTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
		String now = localDateTime.format(dateTimeFormatter);
		return now;
	}

	/**
	 *
	 * 마스킹된 계좌 자르기
	 */
	public String trimAccountNum(String accountNum, int length) {
		String account = accountNum.substring(0, length - 3);
		return account;
	}

	/**
	 * 헤더에 엑세스 토큰넣기
	 */
	public HttpHeaders makeAccessTokenHeader(String access_token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Bearer " + access_token);
		return httpHeaders;
	}

}
