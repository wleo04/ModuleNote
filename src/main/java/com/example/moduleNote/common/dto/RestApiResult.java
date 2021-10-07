package com.example.moduleNote.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestApiResult<T> implements Serializable {
	private static final long serialVersionUID = 918512942152916627L;

	private String resultCode;

	private T content;
}
