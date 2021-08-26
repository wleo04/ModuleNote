package com.example.moduleNote.login.spl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumSex {
	MALE("0"), FEMALE("1");

	private final String value;

	public boolean isMale() {
		return MALE.equals(this);
	}

	public boolean isFemale() {
		return FEMALE.equals(this);
	}

}
