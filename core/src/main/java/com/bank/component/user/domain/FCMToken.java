package com.bank.component.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "fcm_token")
public class FCMToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fcm_token_id")
	private Long id;

	@Column(name = "token")
	private String token;

	@OneToOne
	@JoinColumn(name = "app_user_id")
	AppUser user;

	public FCMToken(String token, AppUser user) {
		this.token = token;
		this.user =  user;
	}
}
