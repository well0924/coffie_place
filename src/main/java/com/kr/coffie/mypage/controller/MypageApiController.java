package com.kr.coffie.mypage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.mypage.service.MypageService;
import com.kr.coffie.mypage.vo.dto.MypageDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/mypage/*")
public class MypageApiController {
	
	private final MypageService service;
	
	@PostMapping("/wishinsert")
	public Map<String,Object>wishinsert(@RequestBody MypageDto.MypageRequestDto dto)throws Exception{
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		int insertresult = 0;
		
		try {
			
			insertresult = service.wishinsert(dto);
			
			if(insertresult>0) {
		
				result.put("common o.k", 200);
				log.info("추가값:"+insertresult);
			}else if(insertresult < 0) {
			
				result.put("fail",400);
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
			result.put(e.getMessage(),500);
		}
	
		return result;
	}

	@PostMapping("/delete/{fid}/{id}")
	public Map<String,Object>wishdelete(
		@PathVariable(value="fid",required=true)Integer favoriteId ,
		@PathVariable(value="id",required = true) String userId,
		@RequestBody MypageDto.MypageResponseDto dto)throws Exception{
				
		Map<String,Object>result = new HashMap<String, Object>();
		
		int deleteresult = 0;
		
		try {
			
			result.put("favoriteId", dto.getFavoriteId());

			result.put("userId", dto.getUserId());
			
			deleteresult = service.wishdelete(favoriteId,userId);
			
			if(deleteresult > 0) {
			
				result.put("common o.k",200);
				log.info("삭제값:"+deleteresult);
			}else if(deleteresult < 0) {
			
				result.put("fail", 400);
			
			}
		
		} catch (Exception e) {
		
			e.printStackTrace();
			result.put(e.getMessage(),500);
		}
	
		return result;
	}
	
	@PostMapping("/wishcheck/{fid}/{id}")
	public int wishcheck(
		@PathVariable(value="id",required = true)String userId,
		@PathVariable(value="fid",required = true)Integer placeId,
		@RequestBody MypageDto.MypageRequestDto dto)throws Exception{

		int resultcheck = 0;
		
		try {
			
			resultcheck = service.wishcheck(userId, placeId);
			log.info("중복여부:"+resultcheck);			
			if(resultcheck == 0) {//처음으로 누른 경우
				wishinsert(dto);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}

		return resultcheck;
	}
}
