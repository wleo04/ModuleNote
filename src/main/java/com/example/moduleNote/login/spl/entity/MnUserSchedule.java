package com.example.moduleNote.login.spl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "MP_USER_SCHEDULE")
public class MnUserSchedule {

	@Id
	@GeneratedValue(generator = "random-key-generator")
	@GenericGenerator(name = "random-key-generator", parameters = @Parameter(name = "key_size", value = "20"), strategy = "com.example.moduleNote.common.util.JpaRandomKeyGenerator")
	@Column(name = "SCHEDULE_ID")
	private String scheduleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false, columnDefinition = "varchar(20)")
	private MpUserInfo mpUserInfo;

	@Column(name = "SCHEDULE_COLOR")
	private String scheduleColor;

	@Column(name = "SCHEDULE_TITLE")
	private String scheduleTitle;
}
