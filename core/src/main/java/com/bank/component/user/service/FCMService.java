package com.bank.component.user.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.bank.component.common.constant.ResponseCode;
import com.bank.component.common.exception.CommonException;
import com.bank.component.user.dto.FCMMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMService {

	private final String API_URL = "https://fcm.googleapis.com/v1/projects/wooribank-error-remittance/messages:send";
	private final ObjectMapper objectMapper;

	public void sendMessageTo(String targetToken, String title, String body) throws IOException {
		final String message = makeMessage(targetToken, title, body);
		final OkHttpClient client = new OkHttpClient();
		final RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), message);
		final Request request = new Request.Builder()
			.url(API_URL)
			.post(requestBody)
			.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
			.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
			.build();
		final Response response = client.newCall(request).execute();

		if (response.code() != 200) {
			throw new CommonException(ResponseCode.INVALID_API_RESPONSE);
		}
	}

	private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
		final FCMMessageDto fcmMessageDto = FCMMessageDto.builder()
			.message(FCMMessageDto.Message.builder()
				.token(targetToken)
				.notification(FCMMessageDto.Notification.builder()
					.title(title)
					.body(body)
					.image(null)
					.build()
				)
				.build()
			)
			.validate_only(false)
			.build();

		return objectMapper.writeValueAsString(fcmMessageDto);

	}

	private String getAccessToken() throws IOException {
		final String firebaseConfigPath = "firebase/firebase_service_key.json";

		final GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
			new ClassPathResource(firebaseConfigPath)
				.getInputStream()).createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

		googleCredentials.refreshIfExpired();

		return googleCredentials.getAccessToken().getTokenValue();
	}

}
