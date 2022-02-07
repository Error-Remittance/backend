package com.bank.api.user.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.component.common.constant.ResponseCode;
import com.bank.component.common.exception.CommonException;
import com.bank.component.user.domain.AppUser;
import com.bank.component.user.domain.FCMToken;
import com.bank.component.user.repository.AppUserRepository;
import com.bank.component.user.vo.AppUserVo;
import com.bank.component.user.vo.SignInRequestVo;
import com.bank.component.user.vo.SignUpRequestVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserService {
	private final AppUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public AppUserVo completeSignUp(SignUpRequestVo requestVo) {

		// 사용자를 구분하는 방법 찾아야함
		final boolean isValidUserId = !userRepository.findTopByUserId(requestVo.getUserId()).isPresent();

		if (!isValidUserId) {
			throw new CommonException(ResponseCode.SIGN_UP_FAILED_FOR_INVALID_INFO);
		}

		final String encryptedPassword = passwordEncoder.encode(requestVo.getPassword());

		final AppUser user = new AppUser(
			requestVo.getUserId(),
			encryptedPassword,
			requestVo.getName(),
			requestVo.getPhoneNumber());

		userRepository.save(user);

		return user.toVo();
	}

	@Transactional
	public AppUserVo signIn(SignInRequestVo requestVo) {

		final Optional<AppUser> userOptional =
			userRepository.findTopByUserId(requestVo.getUserId());

		final AppUser user =
			userOptional.orElseThrow(() -> new CommonException(ResponseCode.USER_NOT_EXISTED));

		if (!passwordEncoder.matches(requestVo.getPassword(), user.getPassword())) {
			throw new CommonException(ResponseCode.INVALID_PASSWORD);
		}

		final FCMToken fcmToken = new FCMToken(requestVo.getFcmToken(), user);
		user.registerToken(fcmToken);

		return user.toVo();
	}

}
