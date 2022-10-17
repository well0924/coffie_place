package com.kr.coffie.exception.dto;

import com.kr.coffie.exception.ResponseCodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	private String message;
	private String code;
	private int status;
	private String detail;
	
	public static ErrorResponse of(ResponseCodes code) {
		
		return null;
	}
}
