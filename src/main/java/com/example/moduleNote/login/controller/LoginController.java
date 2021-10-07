package com.example.moduleNote.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moduleNote.common.configuration.JwtTokenProvider;
import com.example.moduleNote.common.constant.CommonStatusCode;
import com.example.moduleNote.common.dto.RestApiResult;
import com.example.moduleNote.login.service.LoginService;
import com.example.moduleNote.login.spl.entity.AuthRequest;
import com.example.moduleNote.login.spl.repository.MpUserInfoRepository;

@RestController
public class LoginController {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/login")
	public String login() {
		System.out.println("dddd");
		return "main";
	}
	
	
	@PostMapping(value = "/doLogin", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestApiResult<String> doLogin(){
		return new RestApiResult<>(CommonStatusCode.SUCCESS, null);
	}
	
	@PostMapping(value="/loginTest")
	public RestApiResult<String> loginTest(@RequestBody AuthRequest request) throws Exception{
		System.out.println("1");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserId(), request.getLoginPwd()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("2");
		MpUserInfoRepository repository = null;
		String token =jwtTokenProvider.createToken(repository.findByUserId(request.getUserId())); 

		return new RestApiResult<>(CommonStatusCode.SUCCESS, token);
	}
}
