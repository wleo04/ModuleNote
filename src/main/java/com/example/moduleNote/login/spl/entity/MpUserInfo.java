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

@Getter
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
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "USER_NAME", nullable = false, length = 100)
	private String userName;

	@Column(name = "USER_SEX", nullable = false)
	private EnumSex sex;

	@Column(name = "USER_PHONE", nullable = false)
	private int userPhone;

	@Column(name = "INTERNATIONAL_CALL_CODE", nullable = false)
	private int internationalCallCode;

}
