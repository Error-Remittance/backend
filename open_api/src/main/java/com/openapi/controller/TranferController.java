package com.openapi.controller;

import org.springframework.stereotype.Controller;

import com.bank.component.common.CommonResponseMaker;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TranferController {
	private final CommonResponseMaker commonResponseMaker;

}
