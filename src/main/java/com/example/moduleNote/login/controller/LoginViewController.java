package com.example.moduleNote.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {
	@GetMapping("/")
	public String index(){
		return "index";
	}
	@GetMapping("/login")
	public String loginView(){
		return "/login/login";
	}
	
}
