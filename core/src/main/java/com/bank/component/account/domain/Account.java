package com.bank.component.account.domain;

import com.bank.component.account.vo.AccountVo;
import com.bank.component.transaction.domain.Transaction;
import com.bank.component.user.domain.AppUser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "account_id")
	private Long id;

	private String number;
	private String name;
	private double balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "app_user_id")
	private AppUser user;

	@OneToMany(mappedBy = "sentAccount")
	private List<Transaction> sentTransactionHistory = new ArrayList<>();

	@OneToMany(mappedBy = "receivedAccount")
	private List<Transaction> receivedTransactionHistory = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;

	public Account(String number, String name, double balance, AppUser user, Bank bank) {
		this.number = number;
		this.name = name;
		this.balance = balance;
		this.user = user;
		this.bank = bank;
	}

	public void setUser(AppUser wooriUser) {
		wooriUser.getAccountList().add(this);
	}

	public AccountVo toVo() {
		return AccountVo.builder()
			.id(id)
			.bank(bank.getName())
			.number(number)
			.name(name)
			.balance(balance)
			.build();
	}

	public void deposit(double amount) {
		this.balance += amount;
	}

	public void withdraw(double amount) {
		this.balance -= amount;
	}
}
