package com.example.moduleNote.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.moduleNote.common.filter.CustomFilter;
import com.example.moduleNote.login.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomFilter filter;
	
	public SecurityConfigure(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override // 시큐리티에 걸리지 않을 경로 설정
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/loginTest");  
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override protected void configure(HttpSecurity http) throws Exception {
		  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		  
		  
		  /*http.csrf().disable().authorizeRequests().antMatchers("/login")
          .permitAll().anyRequest().authenticated()
          .and().exceptionHandling().and().sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
          */
		  
		  
	} 
		@Bean 
		public BCryptPasswordEncoder bCryptPasswordEncoder() { 
			return new BCryptPasswordEncoder(); 
		}
	
}
