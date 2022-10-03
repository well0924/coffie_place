package com.kr.coffie.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kr.coffie.login.vo.dto.LoginDto;

@Mapper
public interface LoginMapper {
	
	public int userduplicate(String userId)throws Exception;

	public LoginDto.LoginResponseDto loginProc(LoginDto.LoginRequestDto dto)throws Exception;

	public LoginDto.LoginResponseDto idSearch(@Param("userName") String userName,@Param("userEmail") String userEmail)throws Exception;

	public int pwSearch(LoginDto.LoginRequestDto dto)throws Exception;
	
	public int useremailduplocated(String userEmail)throws Exception;
}
