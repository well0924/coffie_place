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

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.notice.service.NoticeService;
import com.kr.coffie.notice.vo.dto.NoticeDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notice/*")
public class NoticeApiController {
	
	private final NoticeService service;
	
	@GetMapping("/list")
	public Map<String,Object>articelist(Criteria cri)throws Exception{
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<NoticeDto.NoticeResponseDto>list = null;
		int totallist = 0;
		
		try {
			
			list = service.noticelist(cri);			
			totallist = service.noticetotalcount(cri);
			result.put("list", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(totallist);
		
		return result;
	}
	
	@PostMapping("/write")
	public Map<String,Object>articleinsert(NoticeDto.NoticeRequestDto dto,FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int insertresult = 0;
		
		try {
			
			insertresult = service.noticeinsert(dto,fvo);
			
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
	public Map<String,Object>articleupdate(@PathVariable("id")Integer boardId,NoticeDto.NoticeRequestDto dto,FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		
		int updateresult = 0;
		
		try {
			updateresult = service.noticeupdate(dto,fvo);
			
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
