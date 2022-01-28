package com.openapi.dto.user;

import lombok.Data;

@Data
public class UserLoginDisconnectResponseDto {
	private String api_tran_id;
	private String api_tran_dtm;
	private String rsp_code;
	private String rsp_message;
	private String user_seq_no;
}
