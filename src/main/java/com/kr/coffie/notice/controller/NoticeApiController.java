package com.kr.coffie.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.notice.service.NoticeService;
import com.kr.coffie.notice.vo.dto.NoticeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags= {"공지게시판 Api"},value="공지게시판에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/notice/*")
public class NoticeApiController {
	
	private final NoticeService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "문서 전체 조회 API",notes="공지게시판에서 글목록을 조회합니다.")
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
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "공지게시판 작성 API",notes="공지게시판에서 글을 작성합니다.")
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
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "공지게시판 수정 API",notes="공지게시판에서 글을 수정합니다.")
	@PutMapping("/update/{id}")
	public Map<String,Object>articleupdate(
			@PathVariable(value="id")Integer boardId,
			NoticeDto.NoticeRequestDto dto,
			FileDto.FileRequestDto fvo)throws Exception{
		
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
	@ApiOperation(value = "공지게시판 삭제 API",notes="공지게시판에서 글을 삭제합니다.")
	@DeleteMapping("/delete/{id}")
	public Map<String,Object>articledelete(@PathVariable(value="id")Integer boardId)throws Exception{
		
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
