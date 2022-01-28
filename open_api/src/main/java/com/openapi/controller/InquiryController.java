package com.openapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.bank.component.common.CommonResponseMaker;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InquiryController {

	private final CommonResponseMaker commonResponseMaker;

}
