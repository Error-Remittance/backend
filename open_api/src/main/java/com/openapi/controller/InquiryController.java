package com.openapi.controller;

import org.springframework.stereotype.Controller;

import com.bank.component.common.CommonResponseMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InquiryController {

	private final CommonResponseMaker commonResponseMaker;

}
