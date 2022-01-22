package com.bank.component.user.domain;

import com.bank.component.account.domain.Account;
import com.bank.component.transaction.domain.ReturnRequest;
import com.bank.component.account.vo.AccountVo;
import com.bank.component.user.vo.AppUserVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "app_user")
public class AppUser {

	@Id @GeneratedValue
	@Column(name = "app_user_id")
	private Long id;

	@Column(name = "user_id")
	private String userId;

	private String password;

	private String name;

	private String phoneNumber;

	@OneToMany(mappedBy = "user")
	private List<Account> accountList = new ArrayList<>();

	@OneToMany(mappedBy = "sentUser")
	private List<ReturnRequest> sentReturnRequestList = new ArrayList<>();

	@OneToMany(mappedBy = "receivedUser")
	private List<ReturnRequest> receivedReturnRequestList = new ArrayList<>();

	@OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private FCMToken fcmToken;

	public AppUser(String userId, String password, String name, String phoneNumber) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public Boolean hasToken() {
		return fcmToken != null;
	}

	public AppUserVo toVo() {
		return AppUserVo.builder()
			.appUserId(id)
			.name(name)
			.userId(userId)
			.phoneNumber(phoneNumber)
			.build();
	}

	public void registerToken(FCMToken fcmToken) {
		this.fcmToken = fcmToken;
	}

	public List<AccountVo> toAccountList() {
		return accountList.stream().map(account -> account.toVo()).collect(Collectors.toList());
	}
}
