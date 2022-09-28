package com.kr.coffie.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
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
import com.kr.coffie.common.page.Paging;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"자유게시판 Api"} ,value="자유게시판에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/board/*")
public class BoardApiController {
	
	private final BoardService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "문서 전체 조회 API",notes="자유게시판에서 글목록을 조회합니다.")
	@GetMapping(value="/list")
	public Map<String,Object>articelist(
			@ApiParam(name="페이징 검색 객체",required = true)
			Criteria cri)throws Exception{
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<BoardDto.BoardResponseDto>list = null;
		
		int totallist =0;
		
		try {
			
			list = service.boardlist(cri);
			
			totallist = service.totalarticle(cri);
			
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
	@ApiOperation(value = "자유게시판 글작성 API",notes="자유게시글에서 글을 작성하는 기능입니다. 작성기능은 로그인이 필요합니다.")
	@PostMapping(value="/write")
	public Map<String,Object>articleinsert(
			@ApiParam(value="게시글 객체",required = true)
			@RequestBody
			@ModelAttribute BoardDto.BoardRequestDto dto,
			@ApiIgnore
			@ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int insertresult = 0;
		
		try {
			fvo.setImgGroup("freeBoard");
			fvo.setFileType("Board");
			
			insertresult = service.boardwrite(dto, fvo);
			
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
	@ApiOperation(value = "자유게시판 글수정 API",notes="자유게시글에서 글을 수정하는 기능입니다.")
	@PutMapping(value="/modify/{id}", produces = MediaType.ALL_VALUE)
	public Map<String,Object>articleupdate(
			@ApiParam(name="게시물 번호",example="1",required = true)
			@PathVariable(value="id")Integer boardId,
			@ApiParam(name="게시물 dto",required = true)
			@RequestBody
			@ModelAttribute 
			BoardDto.BoardRequestDto dto,
			@ApiIgnore
			@ApiParam(name="파일dto",hidden = true)
			@ModelAttribute 
			FileDto.FileRequestDto fvo)throws Exception{
		
		Map<String,Object> result = new HashMap<>();
		
		int updateresult = 0;
		
		try {
			fvo.setImgGroup("freeBoard");
			fvo.setFileType("Board");
			
		    updateresult = service.boardupdate(dto, fvo);
			
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
	@ApiOperation(value = "자유게시판 글삭제 API",notes="자유게시글에서 글을 삭제하는 기능입니다.")
	@DeleteMapping(value="/delete/{id}")
	public Map<String,Object>articledelete(
			@ApiParam(name = "자유게시판번호", required = true, example = "1")
			@PathVariable(value="id",required = true)Integer boardId)throws Exception{
		
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
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "자유게시판 비밀번호확인 API",notes="자유게시글에서 글수정시 비밀번호 확인 기능입니다.")
	@GetMapping(value="/pwcheck/{boardId}/{pwd}")
	public BoardDto.BoardResponseDto passwordCheck(
			@ApiParam(name = "자유게시판번호",required = true, example = "5003")
			@PathVariable(value="boardId")@RequestBody Integer boardId,
			@ApiParam(name = "열람시 사용되는 비밀번호",required = true, example = "1111")
			@PathVariable(value="pwd")@RequestBody Integer passWd)throws Exception{
		
		BoardDto.BoardResponseDto dto = null;
		
		try {
		
			dto = service.passwordcheck(passWd, boardId);		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		
		return dto;
	}
}
