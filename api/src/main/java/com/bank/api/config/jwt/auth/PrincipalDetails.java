package com.bank.api.config.jwt.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.component.user.domain.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails {
	private User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoleList().forEach(r -> {
			authorities.add(() -> r);
		});

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}
}
