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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/*")
public class AdminApiController {

	private final AdminService service;
	
	@PutMapping(value="/memberupdate/{id}")
	public Map<String,Object>memberUpdate(@PathVariable("id")String userId, @RequestBody LoginDto.LoginRequestDto dto)throws Exception{
		
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
	
	@DeleteMapping(value="/memberdelete/{id}")
	public Map<String,Object>memberDelete( @PathVariable("id")String userId)throws Exception{
		
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
	
	
	@PostMapping(value="/selectdelete")
	public List<String>selectmemberdelete(@RequestBody List<String>userId)throws Exception{
	
		try {
			
			service.membercheckdelete(userId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userId;
	}
	
	
	@PostMapping("/autocompetekeyword")
	public Map<String,Object>autocompletekeyword( @RequestParam Map<String,Object>param)throws Exception{
		
		List<Map<String,Object>>list = service.memberautocomplete(param);
		
		param.put("resultList", list);
		
		return param;
	}

}
