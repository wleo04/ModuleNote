package com.example.moduleNote.login.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.moduleNote.login.spl.entity.MpUserInfo;
import com.example.moduleNote.login.spl.repository.MpUserInfoRepository;
import com.example.moduleNote.login.spl.repository.custom.MpUserInfoQueryRepository;

@Service
public class LoginService implements UserDetailsService{
	
	/*
	private List<String> roles = new ArrayList<>(); // SpringSecurity 권한
	
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }*/

	@Autowired
	private MpUserInfoRepository mpUserInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String USER_ID) throws UsernameNotFoundException {
		System.out.println("loginService 시작");
		System.out.println(USER_ID);
		MpUserInfo mpUserInfo = mpUserInfoRepository.findByUserId(USER_ID);
		System.out.println("아아아아");
		System.out.println(mpUserInfo);
		return new User(mpUserInfo.getUserId(),mpUserInfo.getLoginPwd(), new ArrayList<>());
	}
	
}
