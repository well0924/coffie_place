package com.kr.coffie.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kr.coffie.login.mapper.LoginMapper;
import com.kr.coffie.login.vo.LoginUserDetails;
import com.kr.coffie.login.vo.dto.LoginDto;
import com.kr.coffie.login.vo.dto.LoginDto.LoginRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService{
	
	private final LoginMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginDto.LoginRequestDto dto = new LoginRequestDto();
		dto.setUserId(username);
		
		LoginDto.LoginResponseDto result = null;
		try {
			result = mapper.loginProc(dto);
			
			if(result == null) {
				throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LoginUserDetails(result);
	}
}
