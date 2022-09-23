package com.kr.coffie.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kr.coffie.admin.mapper.AdminMapper;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
	
	private final AdminMapper mapper;
	
	public List<LoginDto.LoginResponseDto>memberlist(Criteria cri)throws Exception{
		return mapper.memberlist(cri);
	};

	public LoginDto.LoginResponseDto memeberdetail(String userId)throws Exception{
		return mapper.memeberdetail(userId);
	};
	
	public List<Map<String,Object>>memberautocomplete(Map<String,Object>result)throws Exception{
		return mapper.memberautocomplete(result);
	};
	
	public int memberjoin(LoginDto.LoginRequestDto param)throws Exception{
		return mapper.memberjoin(param);
	};

	public int memberupdate(LoginDto.LoginRequestDto param)throws Exception{
		return mapper.memberupdate(param);
	};

	public int memberdelete(String userId)throws Exception{
		return mapper.memberdelete(userId);
	};

	public void membercheckdelete(List<String> userId)throws Exception{
		mapper.membercheckdelete(userId);
	};

	public int totalmember(Criteria cri)throws Exception{
		return mapper.totalmember(cri);
	};
}
