package com.example.moduleNote.login.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moduleNote.common.constant.CommonStatusCode;
import com.example.moduleNote.common.dto.RestApiResult;

@RestController
@RequestMapping("/login")
public class LoginController {

	@PostMapping(value = "/doLogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestApiResult<String> doLogin(){
		return new RestApiResult<>(CommonStatusCode.SUCCESS, null);
	}
}
