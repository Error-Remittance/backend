package com.bank.api.transaction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.bank.api.account.controller.AppApiV1Controller;
import com.bank.api.transaction.dto.GetReceivedReturnRequestsResponseDto;
import com.bank.api.transaction.dto.GetSentReturnRequestsResponseDto;
import com.bank.api.transaction.dto.MakeReturnRequestRequestDto;
import com.bank.api.transaction.service.ReturnRequestService;
import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.constant.ResponseCode;
import com.bank.component.common.dto.CommonResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReturnRequestController extends AppApiV1Controller {

	private final ReturnRequestService returnRequestService;
	private final CommonResponseMaker commonResponseMaker;

	@PostMapping("/return_requests")
	public CommonResponse<Void> makeReturnRequest(
		@RequestBody MakeReturnRequestRequestDto requestDto) throws IOException {

		returnRequestService.makeReturnRequest(requestDto.toVo());

		return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
	}

	@GetMapping("/return_requests/sending")
	public CommonResponse<GetSentReturnRequestsResponseDto> getSentReturnRequest(
		@RequestParam final String userId) throws IOException {

		final GetSentReturnRequestsResponseDto responseDto = GetSentReturnRequestsResponseDto.of(
			returnRequestService.getSentReturnRequest(userId));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}

	@GetMapping("/return_requests/receiving")
	public CommonResponse<GetReceivedReturnRequestsResponseDto> getReceivedReturnRequest(
		@RequestParam final String userId) throws IOException {

		final GetReceivedReturnRequestsResponseDto responseDto = GetReceivedReturnRequestsResponseDto.of(
			returnRequestService.getReceivedReturnRequest(userId));

		return commonResponseMaker.makeSucceedCommonResponse(responseDto);
	}

	@PostMapping("/return_requests/accept")
	public CommonResponse<Void> acceptReturnRequest(
		@RequestParam final long returnRequestId) throws IOException {

		returnRequestService.acceptReturnRequest(returnRequestId);

		return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
	}

	@PostMapping("/return_requests/report")
	public CommonResponse<Void> reportReturnRequest(
		@RequestParam final Long returnRequestId) throws IOException {

		returnRequestService.reportReturnRequest(returnRequestId);

		return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
	}

}
