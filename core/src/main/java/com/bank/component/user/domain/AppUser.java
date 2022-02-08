package com.bank.component.user.domain;

import com.bank.component.user.vo.AppUserVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class AppUser {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "app_user_id")
	private Long id;

	private String name;
	private String username;
	private String password;

	private String phoneNumber;

	private String accessToken;
	private String refreshToken;
	private String scope;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq_no")
	private AppUserInfo userInfo;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();


	public AppUser(String name, String username, String password, String phoneNumber) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public AppUserVo toVo() {
		return AppUserVo.builder()
			.appUserId(id)
			.name(name)
			.phoneNumber(phoneNumber)
			.build();
	}

	// public List<AccountVo> toAccountList() {
	// 	return accountList.stream().map(account -> account.toVo()).collect(Collectors.toList());
	// }
}
