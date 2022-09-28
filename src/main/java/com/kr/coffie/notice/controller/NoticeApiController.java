package com.kr.coffie.notice.controller;

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

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.notice.service.NoticeService;
import com.kr.coffie.notice.vo.dto.NoticeDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "문서 전체 조회 API",notes="공지게시판에서 글목록을 조회합니다. 회원,어드민 둘 다 사용 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="keyword",value="검색어",example="test",dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page",value="페이지",example="1",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="perPageNum",value="페이지번호",example="5",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="searchType",value="검색타입",example="T",dataType = "String",paramType = "query")
	})
	@GetMapping(value="/list")
	public Map<String,Object>noticearticelist(Criteria cri)throws Exception{
	
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "공지게시판 작성 API",notes="공지게시판에서 글을 작성합니다. 사용은 어드민으로 로그인을 해야 사용할 수 있습니다.")
	@PostMapping(value="/write")
	public Map<String,Object>noticearticleinsert(
			@ApiParam(name="공지게시판에 사용되는 dto",required = true)
			@RequestBody
			@ModelAttribute 
			NoticeDto.NoticeRequestDto dto,
			@ApiParam(hidden = true)
			@ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int insertresult = 0;
		
		try {
			fvo.setImgGroup("noticeBoard");
			fvo.setFileType("Board");
			
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "공지게시판 수정 API",notes="공지게시판에서 글을 수정합니다. 수정은 어드민으로 로그인이 되었을때 사용을 할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시판 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@PutMapping(value="/update/{id}")
	public Map<String,Object>noticearticleupdate(
			@PathVariable(value="id",required = true)Integer boardId,
			@ApiParam(name="공지게시판dto",required = true)
			@RequestBody
			@ModelAttribute 
			NoticeDto.NoticeRequestDto dto,
			@ModelAttribute 
			FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		
		int updateresult = 0;
		
		try {
			fvo.setImgGroup("noticeBoard");
			fvo.setFileType("Board");
			
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "공지게시판 삭제 API",notes="공지게시판에서 글을 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="공지게시글 번호",example="1",dataType = "Intger",paramType = "path")
	})
	@DeleteMapping(value="/delete/{id}")
	public Map<String,Object>noticearticledelete(
			@PathVariable(value="id",required = true)Integer boardId)throws Exception{
		
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
