package com.kr.coffie.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.login.vo.dto.LoginDto;

@Mapper
public interface AdminMapper {
	
	public List<LoginDto.LoginResponseDto>memberlist(Criteria cri)throws Exception;

	public LoginDto.LoginResponseDto memberdetail(String userId)throws Exception;
	
	public List<Map<String,Object>>memberautocomplete(Map<String,Object>result)throws Exception;
	
	public int memberjoin(LoginDto.LoginRequestDto param)throws Exception;

	public int memberupdate(LoginDto.LoginRequestDto param)throws Exception;

	public int memberdelete(String userId)throws Exception;

	public void membercheckdelete(String string)throws Exception;

	public int totalmember(Criteria cri)throws Exception;
}
