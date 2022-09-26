package com.kr.coffie.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.login.vo.dto.LoginDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags = {"어드민 Api"} ,value="어드민페이지에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/*")
public class AdminApiController {

	private final AdminService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "회원수정 API",notes="회원수정페이지에서 회원수정기능")
	@PutMapping(value="/memberupdate/{id}")
	public Map<String,Object>memberUpdate(
			@ApiParam(value="id",name="회원아이디",required = true)
			@PathVariable("id")String userId, 
			@ApiParam(value="dto",name="로그인Dto",required = true)
			@RequestBody LoginDto.LoginRequestDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		int updateresult =0;
		
		try {
			
			updateresult = service.memberupdate(dto);
			
			if(updateresult>0) {
			
				result.put("o.k", 200);
			
			}else if(updateresult < 0) {
				
				result.put("fail", 400);
			
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		
		return result;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "회원삭제 API",notes="회원조회페이지에서 회원을 삭제및 탈퇴하는 기능")
	@DeleteMapping(value="/memberdelete/{id}")
	public Map<String,Object>memberDelete( 
			@ApiParam(value="id",name="회원아이디",required = true)
			@PathVariable("id")String userId)throws Exception{
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		int deleteresult = 0;
		
		try {
		
			deleteresult = service.memberdelete(userId);
			
			if(deleteresult>0) {
			
				result.put("o.k", 200);
			
			}else if(deleteresult<0) {
			
				result.put("fail", 400);
			
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		
		return result;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "회원선택 체크삭제 API",notes="어드민 페이지에서 회원목록에서 체크박스 선택삭제기능")
	@PostMapping(value="/selectdelete")
	public List<String>selectmemberdelete(@RequestBody List<String>userId)throws Exception{
	
		try {
			
			service.membercheckdelete(userId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userId;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "회원자동완성검색 API",notes="어드민페이지에서 검색시 회원을 자동검색하는 기능")
	@PostMapping("/autocompetekeyword")
	public Map<String,Object>autocompletekeyword( @RequestParam Map<String,Object>param)throws Exception{
		
		List<Map<String,Object>>list = service.memberautocomplete(param);
		
		param.put("resultList", list);
		
		return param;
	}

}
