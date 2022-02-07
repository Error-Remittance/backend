package com.bank.openbank.dto.account;

import java.util.List;

import com.bank.openbank.dto.user.AccountDto;

import lombok.Data;

@Data
public class AccountListResponseDto {
	private String api_tran_id;
	private String api_tran_dtm;
	private String rsp_code;
	private String rsp_message;
	private String user_name;
	private int res_cnt;
	private List<AccountDto> res_list;

}
