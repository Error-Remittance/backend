package com.openapi.dto.user;

import lombok.Data;

@Data
public class UserUnlinkRequestDto {
	private String client_user_code;
	private String user_seq_no;
}
