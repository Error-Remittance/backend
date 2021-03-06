package com.bank.component.account.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bank {

	@Id
	@Column(name = "bank_code_std" , length = 3)
	private String bankCodeStd;

	private String bankName;

	// public Bank(String name) {
	// 	this.name = name;
	// }
}
