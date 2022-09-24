package com.kr.coffie.login.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kr.coffie.login.mapper.LoginMapper;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Service
@AllArgsConstructor
public class LoginService {
	
	private final LoginMapper mapper;
	
	private final PasswordEncoder encoder;
	
	public int userduplicate(String userId)throws Exception{
		return mapper.userduplicate(userId);
	};

	public LoginDto.LoginResponseDto loginProc(LoginDto.LoginRequestDto dto)throws Exception{
		return mapper.loginProc(dto);
	};

	public LoginDto.LoginResponseDto idSearch(@Param("userName") String userName,@Param("userEmail") String userEmail)throws Exception{
		return mapper.idSearch(userName, userEmail);
	};

	public int pwSearch(LoginDto.LoginRequestDto dto)throws Exception{
		
		String userpw = dto.getUserPw();
		String encodepw = encoder.encode(userpw);
		
		dto.setUserPw(encodepw);
		
		return mapper.pwSearch(dto);
	};

}
