package com.kr.coffie.place.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.place.service.PlaceService;
import com.kr.coffie.place.vo.dto.PlaceDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/place/*")
public class PlaceApiController {
	
	private final PlaceService service;
	
	@PostMapping("/autocompetekeyword")
	public Map<String,Object>autocompletekeyword(@RequestParam Map<String,Object>param)throws Exception{
		
		List<Map<String,Object>>list = service.placeautocomplete(param);
		
		param.put("resultList", list);
		
		return param;
	}
	
	@PostMapping("/placeregister")
	public Map<String,Object>placeregister(
		@RequestBody PlaceDto.PlaceRequestDto dto,
		@ModelAttribute FileDto.ImageRequestDto imgvo)throws Exception{
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		int registerresult = 0;
		
		try {
	
			dto.setPlaceAuthor("admin");
			
			imgvo.setImgGroup("coffieplace");
			imgvo.setFileType("place");
			
			registerresult = service.placeregister(dto,imgvo);
			
			if(registerresult > 0 ) {
			
				result.put("common o.k", 200);
			
			}else if( registerresult < 0) {
			
				result.put("fail", 400);
			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		
		return result;
	}
	
	@PutMapping("/placeupdate/{id}")
	public Map<String,Object>placeupdate(
			@PathVariable(value="id",required = true)Integer placeId,
			@RequestBody PlaceDto.PlaceRequestDto dto,
			@ModelAttribute FileDto.ImageRequestDto imgvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		int updateresult = 0;
		
		try {
	
			dto.setPlaceAuthor("admin");
			
			imgvo.setImgGroup("coffieplace");
			imgvo.setFileType("place");
			
			updateresult = service.placeupdate(dto,imgvo);
			
			if(updateresult>0) {
			
				result.put("common o.k", 200);
			
			}else if(updateresult < 0) {
			
				result.put("fail", 400);
			}
		
		
		} catch (Exception e) {
		
			e.printStackTrace();
			
			result.put(e.getMessage(), 500);
		}
		
		return result;
	}
	
	@DeleteMapping("/placedelete/{id}")
	public Map<String,Object>placedelete(@PathVariable("id")Integer placeId)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		int deleteresult = 0;
		
		try {
			
			deleteresult = service.placedelete(placeId);
			
			if(deleteresult > 0) {
	
				result.put("common o.k", 200);
			
			}else if( deleteresult < 0) {
			
				result.put("fail", 400);
			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		
		}

		return result;
	}

}
