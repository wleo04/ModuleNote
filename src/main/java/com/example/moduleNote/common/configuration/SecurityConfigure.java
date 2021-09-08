package com.example.moduleNote.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.moduleNote.login.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
	@Autowired
	private LoginService loginService;
	
	@Override public void configure(WebSecurity web) {
		web.ignoring();  
	}
	
	@Override protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests() 
		// /about 요청에 대해서는 로그인을 요구함 
		.antMatchers("/").authenticated() 
		// /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함 
		.antMatchers("/admin").hasRole("ADMIN") 
		// 나머지 요청에 대해서는 로그인을 요구하지 않음 
		.anyRequest().permitAll() .and()
		// 로그인하는 경우에 대해 설정함 
		.formLogin() 
		// 로그인 페이지를 제공하는 URL을 설정함 
		//.loginPage("/login")
		// 로그인 성공 URL을 설정함 
		.successForwardUrl("/") 
		// 로그인 실패 URL을 설정함 
		.failureForwardUrl("/index")
		.permitAll();
		
		//.and() 
		//.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);  커스텀 필터는 jwt용임  
		} 
		@Bean 
		public BCryptPasswordEncoder bCryptPasswordEncoder() { 
			return new BCryptPasswordEncoder(); 
		}
		
		@Override
		   public void configure(AuthenticationManagerBuilder auth) throws Exception {
		       auth.userDetailsService(loginService).passwordEncoder(bCryptPasswordEncoder());
		   }
	
}
