package com.bank.component.account.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "bank")
public class Bank {

	@Id @GeneratedValue
	@Column(name = "bank_id")
	private Long id;

	private String name;

	public Bank(String name) {
		this.name = name;
	}
}
