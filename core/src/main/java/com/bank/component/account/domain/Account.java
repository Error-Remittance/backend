package com.bank.component.account.domain;

import com.bank.component.transaction.domain.Transaction;
import com.bank.component.user.domain.UserInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class Account {

	@Id
	@Column(name = "fintech_use_num", length = 24)
	private String fintechUseNum;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_code_std")
	private Bank bank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq_no")
	private UserInfo userInfo;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions = new ArrayList<>();

	private String savingsBankName;
	private String accountSeq;
	private String accountHolderName;
	private String accountHolderType;
	private String accountType;
	private String inquiryAgreeYn;
	private String transferAgreeYn;
	private String payerNum;

	private int balanceAmt;
	private int availableAmt;

	/**
	 * 양방향 연관관계 메서드
	 */
	public void setUserInfo(UserInfo userInfo){
		this.userInfo = this.userInfo;
		this.userInfo.getAccounts().add(this);
	}
	// public Account(String number, String name, double balance, AppUser user, Bank bank) {
	// 	this.number = number;
	// 	this.name = name;
	// 	this.balance = balance;
	// 	this.user = user;
	// 	this.bank = bank;
	// }

	// public void setUser(AppUser wooriUser) {
	// 	wooriUser.getAccountList().add(this);
	// }
	//
	// public AccountVo toVo() {
	// 	return AccountVo.builder()
	// 		.id(id)
	// 		.bank(bank.getName())
	// 		.number(number)
	// 		.name(name)
	// 		.balance(balance)
	// 		.build();
	// }
	//
	// public void deposit(double amount) {
	// 	this.balance += amount;
	// }
	//
	// public void withdraw(double amount) {
	// 	this.balance -= amount;
	// }
}
