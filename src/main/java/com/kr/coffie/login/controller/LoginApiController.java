package com.kr.coffie.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.common.email.EmailService;
import com.kr.coffie.login.service.LoginService;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/login/")
public class LoginApiController {
	
	private final LoginService service;
	private final AdminService adminservice;
	private final EmailService emailservice;
	
	@GetMapping("/idcheck/{id}")
	public Map<String,Object>idCheck(@PathVariable("id")String userId)throws Exception{
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
	
	@GetMapping("/idsearch/{userName}/{userEmail}")
	public LoginDto.LoginResponseDto idsearch(@PathVariable("userName")@RequestBody String userName,@PathVariable("userEmail")@RequestBody String userEmail)throws Exception{
		LoginDto.LoginResponseDto dto = null;
		
		try {
			dto =service.idSearch(userName, userEmail);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
	@PostMapping(value="/memberjoin")
	public Map<String,Object>memberJoin( @RequestBody LoginDto.LoginRequestDto dto)throws Exception{
		
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
//////////////////////////////////////
	
	@PostMapping("/emailsend")
	public ResponseEntity<?>emailsender(@RequestBody String useremail)throws Exception{
		ResponseEntity<String>entity = null;
		
		try {
			 emailservice.sendSimpleMessage(useremail);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@GetMapping("/tmppassword")
	public String tmppassword(LoginDto.LoginRequestDto dto)throws Exception{
		return null;
	}
	
	@PutMapping("/updatepw")
	public Map<String,Object>updatepw(@RequestBody LoginDto.LoginRequestDto dto)throws Exception{
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
