package com.bank.openbank.dto.user;

import lombok.Data;

@Data
public class UserUnlinkRequestDto {
	private String client_user_code;
	private String user_seq_no;

	public void setUserUnlinkRequestDto(String client_user_code) {
		this.client_user_code = client_user_code;
	}
}
