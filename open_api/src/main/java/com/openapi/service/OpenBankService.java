package com.openapi.service;

import org.springframework.stereotype.Service;

import com.openapi.api.OpenBankApiClient;
import com.openapi.dto.oauth.AuthrozationResponseDto;
import com.openapi.dto.oauth.IssueTokenRequestDto;
import com.openapi.dto.oauth.IssueTokenResponseDto;
import com.openapi.dto.oauth.RefreshTokenRequestDto;
import com.openapi.dto.oauth.RefreshTokenResponseDto;
import com.openapi.dto.user.UserInfoRequestDto;
import com.openapi.dto.user.UserInfoResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenBankService {

	private final OpenBankApiClient openBankApiClient;

	public AuthrozationResponseDto authorizeClient(){
		return openBankApiClient.authrizationClient();
	}

	public IssueTokenResponseDto requestToken(IssueTokenRequestDto issueTokenRequestDto) {
		return openBankApiClient.requestToken(issueTokenRequestDto);
	}

	public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
		return openBankApiClient.refreshToken(refreshTokenRequestDto);
	}

	public UserInfoResponseDto requestUserInfo(UserInfoRequestDto userInfoRequestDto){
		return openBankApiClient.requestUserInfo(userInfoRequestDto);
	}

}
