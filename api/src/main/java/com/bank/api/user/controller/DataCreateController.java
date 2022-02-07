package com.bank.api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import com.bank.api.user.service.DataCreateService;
import com.bank.component.common.CommonResponseMaker;
import com.bank.component.common.constant.ResponseCode;
import com.bank.component.common.dto.CommonResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DataCreateController {

	private final DataCreateService dataCreateService;
	private final CommonResponseMaker commonResponseMaker;

	@PostMapping("/data/create")
	public CommonResponse<Void> dataCreate() throws IOException {

		dataCreateService.createData();

		return commonResponseMaker.makeEmptyInfoCommonResponse(ResponseCode.SUCCESS);
	}
}
