package com.bank.api.account.dto;

import com.bank.component.account.vo.AccountVo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class AccountDto {
	private Long id;
	private String bank;
	private String number;
	private String name;
	private double balance;

	public static AccountDto of(AccountVo vo) {
		return builder()
			.id(vo.getId())
			.bank(vo.getBank())
			.number(vo.getNumber())
			.name(vo.getName())
			.balance(vo.getBalance())
			.build();
	}
}
