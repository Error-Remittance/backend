package com.openapi.dto.user;

import lombok.Data;

@Data
public class UserLoginDisconnectRequestDto {
	private String client_use_code;
	private String user_seq_no;
}
