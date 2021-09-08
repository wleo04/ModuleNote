package com.example.moduleNote.login.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.moduleNote.login.spl.entity.MpUserInfo;

public interface LoginService extends UserDetailsService{
	Integer save(MpUserInfo mpUserInfo);

}
