package com.kr.coffie.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.notice.service.NoticeService;
import com.kr.coffie.notice.vo.dto.NoticeDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/notice/*")
public class NoticeApiController {
	
	private final NoticeService service;
	
	@GetMapping("/list")
	public Map<String,Object>articelist(Criteria cri)throws Exception{
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<NoticeDto.NoticeResponseDto>list = null;
		
		try {
			list = service.noticelist(cri);
			
			log.info("목록:"+list);
			
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping("/write")
	public Map<String,Object>articleinsert(@RequestBody NoticeDto.NoticeRequestDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int insertresult = 0;
		
		try {
			
			insertresult = service.noticeinsert(dto);
			
			if(insertresult >0) {
				result.put("Common o.k", 200);
			}else if(insertresult < 0) {
				result.put("bad request", 400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		return result;
	}
	
	@PutMapping("/update/{id}")
	public Map<String,Object>articleupdate(@PathVariable("id")Integer boardId,@RequestBody NoticeDto.NoticeRequestDto dto)throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		
		int updateresult = 0;
		
		try {
			updateresult = service.noticeupdate(dto);
			
			if(updateresult > 0) {
				result.put("common o.k", 200);
			}else if(updateresult < 0) {
				result.put("bad request", 400);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		return result;
	}
	
	@DeleteMapping("/delete/{id}")
	public Map<String,Object>articledelete(@PathVariable("id")Integer boardId)throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		
		int deleteresult = 0;
		
		try {
			deleteresult = service.noticedelete(boardId);
			
			if(deleteresult > 0) {
				result.put("common o.k", 200);
			}else if(deleteresult < 0) {
				result.put("bad request", 400);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		return result;
	}

}
