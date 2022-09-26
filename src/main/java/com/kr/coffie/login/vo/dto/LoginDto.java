package com.kr.coffie.login.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class LoginDto {
	
	@ApiModel(value="로그인 요청 dto",description = "로그인 요청에 필요한 dto")
	@Getter
	@Setter
	@Builder
	@ToString
	@RequiredArgsConstructor
	@AllArgsConstructor
	public static class LoginRequestDto{
		
		@ApiModelProperty(value="회원아이디",example="well4149",required = true)
		private String userId;
		@ApiModelProperty(value="회원비밀번호",example="4149",required = true)
		private String userPw;
		@ApiModelProperty(value="회원이름",example="tester",required = true)
		private String userName;
		@ApiModelProperty(value="회원나이",example="19",required = true)
		private String userAge;
		@ApiModelProperty(value="회원전화번호",example="010-4148-2353",required = true)
		private String userPhone;
		@ApiModelProperty(value="회원성별",example="남",required = true)
		private String userGender;
		@ApiModelProperty(value="회원이메일",example="well4149@naver.com",required = true)
		private String userEmail;
		@ApiModelProperty(value="회원주소",example="well4149",required = true)
		private String userAddr1;
		@ApiModelProperty(value="회원주소2",example="well4149")
		private String userAddr2;
		@ApiModelProperty(value="회원권한",example="ADMIN")
		private String userAuth;
		@ApiModelProperty(value="회원등록일",example="2022-09-26 12:34",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = Shape.STRING)
		private LocalDateTime createdAt;
	}
	
	@ApiModel(value="로그인 응답 dto",description = "로그인 응답에 필요한 dto")
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor 
	@RequiredArgsConstructor
	public static class LoginResponseDto{
		
		@ApiModelProperty(value="회원아이디",example="well4149",required = true)
		private String userId;
		@ApiModelProperty(value="회원비밀번호",example="4149",required = true)
		private String userPw;
		@ApiModelProperty(value="회원이름",example="tester",required = true)
		private String userName;
		@ApiModelProperty(value="회원전화번호",example="010-4148-2353",required = true)
		private String userPhone;
		@ApiModelProperty(value="회원나이",example="19",required = true)
		private String userAge;
		@ApiModelProperty(value="회원성별",example="남",required = true)
		private String userGender;
		@ApiModelProperty(value="회원이메일",example="well4149@naver.com",required = true)
		private String userEmail;
		@ApiModelProperty(value="회원주소",example="well4149",required = true)
		private String userAddr1;
		@ApiModelProperty(value="회원주소2",example="well4149")
		private String userAddr2;
		@ApiModelProperty(value="회원권한",example="ADMIN")
		private String userAuth;
		@ApiModelProperty(value="회원등록일",example="2022-09-27 12:34",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = Shape.STRING)
		private LocalDateTime createdAt;
		
	}

}
