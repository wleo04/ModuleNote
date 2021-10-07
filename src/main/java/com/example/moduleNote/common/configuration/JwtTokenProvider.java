package com.example.moduleNote.common.configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.inject.Qualifier;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.moduleNote.login.spl.entity.MpUserInfo;
import com.example.moduleNote.login.spl.repository.MpUserInfoRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	private final       int    ACCESS_TOKEN_VALID_SECOND    = 10 * 60; 			// 인증기간(30분)
    private final 	  	int    REFRESH_TOKEN_VALID_SECOND   = 30 * 60; // 재발급 기간(30분)
	
	@Value("${spring.jwt.secret}")
	private String secretKey;
	@Value("${spring.jwt.prefix}")
	private String jwtPreFix;
	@Value("${spring.jwt.upload-token.key}")
	private String UPLOAD_KEY;
	@Value("${spring.jwt.upload-token.value}")
	private String UPLOAD_VALUE;
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	MpUserInfoRepository mpUserInfoRepository;
	
	  public String createToken(MpUserInfo mpUserInfo) {
	        Claims claims = Jwts.claims().setSubject(mpUserInfo.getUserId()); // pk 설정 
	        claims.put("userId", mpUserInfo.getUserId());
	        claims.put("expired", Date.from(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_VALID_SECOND).atZone(ZoneId.systemDefault()).toInstant()));
	        
	        String token = Jwts.builder()
	                .setClaims(claims) // 데이터
	                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))   // 토큰 발행 일자
	                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_VALID_SECOND).atZone(ZoneId.systemDefault()).toInstant())) // 만료 기간
	                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret 값 
	                .compact(); // Token 생성
	        System.out.println(token);
	        return token;
	    }
	
	 // 인증 성공시 SecurityContextHolder에 저장할 Authentication 객체 생성
    public Authentication getAuthentication(String token) {
    	System.out.println("인증 정보 생성 : " + token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt Token에서 User PK 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }
    
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("Authorization");
    }
    
    // Jwt Token의 유효성 및 만료 기간 검사
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
