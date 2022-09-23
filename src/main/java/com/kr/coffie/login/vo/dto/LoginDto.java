package com.kr.coffie.login.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class LoginDto {
	
	@Getter
	@Setter
	@Builder
	@ToString
	@RequiredArgsConstructor
	@AllArgsConstructor
	public static class LoginRequestDto{
		
		private String userId;
		
		private String userPw;
		
		private String userName;
		
		private String userAge;
		
		private String userPhone;
		
		private String userGender;
		
		private String userEmail;
		
		private String userAddr1;
		
		private String userAddr2;
		
		private String userAuth;
		
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
	}
	
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor 
	@RequiredArgsConstructor
	public static class LoginResponseDto{
		
		private String userId;
	
		private String userPw;
		
		private String userName;
		
		private String userPhone;
		
		private String userAge;
		
		private String userGender;
		
		private String userEmail;
		
		private String userAddr1;
		
		private String userAddr2;
		
		private String userAuth;
		
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
		
	}

}
