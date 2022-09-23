package com.kr.coffie.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.board.service.BoardService;
import com.kr.coffie.board.vo.dto.BoardDto;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/board/*")
public class BoardApiController {
	
	private final BoardService service;
	
	@GetMapping("/list")
	public Map<String,Object>articelist(Criteria cri)throws Exception{
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<BoardDto.BoardResponseDto>list = null;
		
		try {
			list = service.boardlist(cri);
			
			log.info("목록:"+list);
			
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@PostMapping("/write")
	public Map<String,Object>articleinsert(@ModelAttribute BoardDto.BoardRequestDto dto,@ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int insertresult = 0;
		
		try {
			
			insertresult = service.boardwrite(dto, fvo);
			log.info(insertresult);
			
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
	
	@PutMapping("/modify/{id}")
	public Map<String,Object>articleupdate(@PathVariable("id")Integer boardId,@ModelAttribute BoardDto.BoardRequestDto dto,@ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		
		int updateresult = 0;
		
		try {
			updateresult = service.boardupdate(dto, fvo);
			
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
			deleteresult = service.boarddelete(boardId);
			
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
	
	@GetMapping(value="/pwcheck/{passwd}/{id}")
	public BoardDto.BoardResponseDto passwordCheck(
			@PathVariable("passwd")@RequestBody Integer passWd,
			@PathVariable("id")@RequestBody Integer boardId)throws Exception{
	
		BoardDto.BoardResponseDto dto = null;
		
		try {
		
			dto = service.passwordcheck(passWd, boardId);		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		return dto;
	}
}
