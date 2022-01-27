package com.openapi.dto.oauth;

import lombok.Data;

@Data
public class AuthrozationResponseDto {
	private String code; // 사용자 인증 시 반환되는 코드
	private String scope; // access tokne 권한 범위
	private String client_info; // 이용 기관에 세팅한 client_info 값 그대로 반환
	private String state; // 요청 시 이용기관이 세팅한 state 값을 그대로 전달

}
