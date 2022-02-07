package com.bank.openbank.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OpenInquiryController {

	private final CommonResponseMaker commonResponseMaker;

}
