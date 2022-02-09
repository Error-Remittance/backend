package com.bank.component.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bank.component.account.domain.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AppUserInfo {

	@Id
	@Column(name = "user_seq_no", length = 10)
	private String userSeqNo;

	private String userCi;
	private String userName;
	private int accountCount;

	@OneToOne(mappedBy = "userInfo")
	private AppUser user;

	@OneToMany(mappedBy = "userInfo")
	private List<Account> accounts = new ArrayList<>();


}
