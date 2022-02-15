package com.bank.component.user.domain;

import com.bank.component.user.vo.AppUserVo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String username;
	private String password;
	private String roles;
	private boolean enabled;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_seq_no")
	private UserInfo userInfo;


	public List<String> getRoleList() {
		if (roles.length() > 0) {
			return Arrays.asList(roles.split(","));
		}
		return new ArrayList<>();
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.roles = "ROLE_USER";
		this.enabled = true;
	}


	public AppUserVo toVo() {
		return AppUserVo.builder()
			.appUserId(id)
			.build();
	}

	// public List<AccountVo> toAccountList() {
	// 	return accountList.stream().map(account -> account.toVo()).collect(Collectors.toList());
	// }
}
