package com.example.moduleNote.login.spl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.example.moduleNote.login.spl.enums.EnumSex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MP_USER_INFO")
public class MpUserInfo implements Serializable {

	private static final long serialVersionUID = -1259131021764444991L;

	@Id
	@GeneratedValue(generator = "random-key-generator")
	@GenericGenerator(name = "random-key-generator", parameters = @Parameter(name = "key_size", value = "20"), strategy = "com.example.moduleNote.common.util.JpaRandomKeyGenerator")
	private Integer id;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "LOGIN_ID")
	private String loginId;
	
	@Column(name = "LOGIN_PWD")
	private String loginPwd;

	@Column(name = "USER_NAME", nullable = false, length = 100)
	private String userName;

	@Column(name = "USER_SEX", nullable = false)
	private EnumSex sex;

	@Column(name = "USER_PHONE", nullable = false)
	private int userPhone;

	@Column(name = "INTERNATIONAL_CALL_CODE", nullable = false)
	private int internationalCallCode;
	
	
	public MpUserInfo(Integer id,String loginId, String userName, String loginPwd) {
		this.id = id;
		this.loginId = loginId;
		this.userName = userName;
		this.loginPwd = loginPwd;
	}
	
	 public MpUserInfo toEntity() {
	      return new MpUserInfo(id,loginId,userName,loginPwd);
	  }

}
