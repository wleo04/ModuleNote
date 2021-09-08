package com.example.moduleNote.login.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import  org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.moduleNote.login.spl.entity.MpUserInfo;
import com.example.moduleNote.login.spl.repository.MpUserInfoRepository;
import com.example.moduleNote.login.spl.repository.custom.MpUserInfoQueryRepository;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private MpUserInfoRepository mpUserInfoRepository;
	
	/*
	public MpUserInfo userVO(String loginId, String loginPwd) {
		MpUserInfo userVO = loginRepository.getUserInfo(loginId, loginPwd);
		return userVO;
	}*/


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  Optional<MpUserInfo> memberEntityWrapper = mpUserInfoRepository.findByuserName(username);
		  MpUserInfo memberEntity = memberEntityWrapper.orElse(null);

	        List<GrantedAuthority> authorities = new ArrayList<>();

	        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

	        return new User(memberEntity.getLoginId(), memberEntity.getLoginPwd(), authorities);
	}


	@Override
	public Integer save(MpUserInfo mpUserInfo) {
		MpUserInfo member = mpUserInfo.toEntity();
	        //member.setRegDt(LocalDateTime.now());

	        // 비밀번호 암호화
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        member.setLoginPwd(passwordEncoder.encode(member.getLoginPwd()));
	        return mpUserInfoRepository.save(member).getId();
	}
}
