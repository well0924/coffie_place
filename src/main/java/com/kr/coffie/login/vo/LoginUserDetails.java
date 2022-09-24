package com.kr.coffie.login.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class LoginUserDetails extends User{
	
	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX ="ROLE_";
	
	public LoginUserDetails(LoginDto.LoginResponseDto dto) {
		super(dto.getUserId(),dto.getUserPw(),makeGrantedAuthorities(dto.getUserAuth()));
	}
	
	private static List<GrantedAuthority> makeGrantedAuthorities(String roleName) {	
		log.info("------userdetail----------");
		log.info(roleName);
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(ROLE_PREFIX+roleName));
		log.info("userdetail:"+list);
		return list;
	}
	
}
