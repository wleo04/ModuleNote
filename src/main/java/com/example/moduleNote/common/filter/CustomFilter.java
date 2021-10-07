package com.example.moduleNote.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.moduleNote.common.configuration.JwtTokenProvider;
import com.example.moduleNote.login.service.LoginService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CustomFilter extends OncePerRequestFilter{
	private JwtTokenProvider jwtTokenProvider;
	
	public CustomFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Autowired
	private LoginService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		System.out.println("필터 들어옴");
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
	    String userid = null;
		System.out.println("token : "+token);
		if(token != null && jwtTokenProvider.validateToken(token)) {
			Authentication auth = jwtTokenProvider.getAuthentication(token); // 인증 객체 생성
			SecurityContextHolder.getContext().setAuthentication(auth);  // SecurityContextHolder에 인증 객체 저장
		}
		System.out.println("필터끝");
		filterChain.doFilter(request, response);
		
	}

}
