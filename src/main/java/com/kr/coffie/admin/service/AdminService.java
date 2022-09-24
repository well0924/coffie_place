package com.kr.coffie.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kr.coffie.admin.mapper.AdminMapper;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
	
	private final AdminMapper mapper;
	
	private final PasswordEncoder encoder;
	
	public List<LoginDto.LoginResponseDto>memberlist(Criteria cri)throws Exception{
		return mapper.memberlist(cri);
	};

	public LoginDto.LoginResponseDto memberdetail(String userId)throws Exception{
		return mapper.memberdetail(userId);
	};
	
	public List<Map<String,Object>>memberautocomplete(Map<String,Object>result)throws Exception{
		return mapper.memberautocomplete(result);
	};
	
	public int memberjoin(LoginDto.LoginRequestDto dto)throws Exception{
		
		String userpw = dto.getUserPw();
		String encodepw = encoder.encode(userpw);
		
		dto.setUserPw(encodepw);
		
		return mapper.memberjoin(dto);
	};

	public int memberupdate(LoginDto.LoginRequestDto dto)throws Exception{
		
		String userpw = dto.getUserPw();
		String encodepw = encoder.encode(userpw);
		dto.setUserPw(encodepw);
		
		return mapper.memberupdate(dto);
	};

	public int memberdelete(String userId)throws Exception{
		return mapper.memberdelete(userId);
	};

	public void membercheckdelete(List<String> userId)throws Exception{
		
		for(int i = 0; i<userId.size();i++) {
			mapper.membercheckdelete(userId.get(i));
		}
	};

	public int totalmember(Criteria cri)throws Exception{
		return mapper.totalmember(cri);
	};
}
