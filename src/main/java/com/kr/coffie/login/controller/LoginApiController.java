package com.kr.coffie.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.login.service.LoginService;
import com.kr.coffie.login.vo.dto.LoginDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags= {"로그인 Api"},value="로그인에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/login/")
public class LoginApiController {
	
	private final LoginService service;
	
	private final AdminService adminservice;	
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "아이디 중복체크 API",notes="아이디 중복체크에 필요한 api입니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="회원아이디",example="well4149",dataType = "String",paramType = "path")
	})
	@GetMapping("/idcheck/{id}")
	public Map<String,Object>idCheck(
			@PathVariable(value="id",required = true)String userId)throws Exception{
		
		Map<String,Object>result = new HashedMap<String,Object>();
		
		int checkresult = 0;
		
		try {
			checkresult = service.userduplicate(userId);
			
			if(checkresult > 0) {
				result.put("userId",checkresult);
			}else {
				result.put("userId",checkresult);
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "아이디 찾기 API",notes="아이디 찾기에 필요한 api입니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="userName",value="회원이름",example="tester1",dataType = "String",paramType = "path"),
		@ApiImplicitParam(required = true,name="userEmail",value="회원이메일",example="well414965@gmail.com",dataType = "String",paramType = "path")
	})
	@GetMapping("/idsearch/{userName}/{userEmail}")
	public LoginDto.LoginResponseDto idsearch(@PathVariable(value="userName") String userName, @PathVariable(value="userEmail") String userEmail)throws Exception{
		
		LoginDto.LoginResponseDto dto = null;
		
		try {
			
			dto =service.idSearch(userName, userEmail);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})	
	@ApiOperation(value = "회원가입 API",notes="회원가입에 필요한 api입니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required =true,name="userId",value="회원아이디",example="well4149",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userPw",value="회원비밀번호",example="1234",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userName",value="회원이름",example="tester",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userAge",value="회원나이",example="12",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(required = true,name="userGender",value="회원성별",example="남성",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userPhone",value="회원전화번호",example="010-3485-3453",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userEmail",value="회원이메일",example="well41495@gmail.com",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userAddr1",value="회원주소",example="서울시 강북구 수유2동",dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="userAddr2",value="회원상세주소",example="7동806호",dataType = "String",paramType = "query"),
		@ApiImplicitParam(required = true,name="userAuth",value="회원권한",example="USER",dataType = "String",paramType = "query")
	})
	@PostMapping(value="/memberjoin")
	public Map<String,Object>memberJoin(
			@RequestBody 
			LoginDto.LoginRequestDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		int joinresult = 0;
		
		try {
			
			joinresult = adminservice.memberjoin(dto);
			
			if(joinresult > 0) {
				
				result.put("join!", 200);
				
			}else if(joinresult < 0) {
				
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "비밀번호수정 API",notes="회원가입 페이지에서 비밀번호수정에 필요한 api입니다.")
	@PutMapping("/updatepw")
	public Map<String,Object>updatepw(
			@ApiParam(name="회원Dto",required = true)
			@RequestBody LoginDto.LoginRequestDto dto)throws Exception{
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		int updateResult = 0;
		
		try {
			updateResult = service.pwSearch(dto);
			
			if(updateResult >0) {
				result.put("resultCode", 200);
			}else if(updateResult < 0) {
				result.put("resultCode", 400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("resultCode", 500);
		}
		
		return result;
	}
}
