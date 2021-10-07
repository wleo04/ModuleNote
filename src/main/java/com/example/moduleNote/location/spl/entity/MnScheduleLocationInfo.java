package com.example.moduleNote.location.spl.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

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

import com.example.moduleNote.login.spl.entity.MnUserSchedule;
import com.example.moduleNote.login.spl.entity.MpUserInfo;

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
@Table(name = "MN_SCHEDULE_LOCATION_INFO")
public class MnScheduleLocationInfo implements Serializable {

	private static final long serialVersionUID = -2548580912927763681L;

	@Id
	@GeneratedValue(generator = "random-key-generator")
	@GenericGenerator(name = "random-key-generator", parameters = @Parameter(name = "key_size", value = "20"), strategy = "com.example.moduleNote.common.util.JpaRandomKeyGenerator")
	@Column(name = "LOCATION_ID")
	private String locationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "SCHEDULE_ID", nullable = false, columnDefinition = "varchar(20)")
	private MnUserSchedule mnUserSchedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false, columnDefinition = "varchar(20)")
	private MpUserInfo mpUserInfo;

	@Column(name = "START_DATE_TIME")
	private ZonedDateTime startDateTime;

	@Column(name = "END_DATE_TIME")
	private ZonedDateTime endDateTime;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "LATITUDE", nullable = false)
	private double latitude;

	@Column(name = "LONGITUDE", nullable = false)
	private double longitude;
}
