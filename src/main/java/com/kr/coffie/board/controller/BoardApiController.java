package com.kr.coffie.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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
import com.kr.coffie.exception.ResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "게시글 전체 조회 API",notes="자유게시판에서 글목록을 조회합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="keyword",value="검색어",example="test",dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page",value="페이지",example="1",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="perPageNum",value="페이지번호",example="5",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="searchType",value="검색타입",example="T",dataType = "String",paramType = "query")
	})
	@GetMapping(value="/list")
	public ResponseDto<List<BoardDto.BoardResponseDto>> articelist(Criteria cri)throws Exception{
		
		List<BoardDto.BoardResponseDto>list = null;
		
		int totallist =0;
		
		list = service.boardlist(cri);
		totallist = service.totalarticle(cri);
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(totallist);
		
		return new ResponseDto<>(HttpStatus.OK.value(),list);
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "자유게시판 글단일조회 API",notes="자유게시글에서 글을 조회하는 기능입니다. 기능은 로그인이 필요합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",required = true,value="게시글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@GetMapping("/boarddetai/{id}")
	public ResponseDto<BoardDto.BoardResponseDto> boarddetail(@PathVariable(value="id",required = true)Integer boardId)throws Exception{
			
		BoardDto.BoardResponseDto dto = null;
		
		dto = service.boarddetail(boardId);		
		
		return new ResponseDto<>(HttpStatus.OK.value(),dto);
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "자유게시판 글작성 API",notes="자유게시글에서 글을 작성하는 기능입니다. 작성기능은 로그인이 필요합니다.")
	@PostMapping(value="/write")
	public ResponseDto<?>articleinsert(
			@Valid @ApiParam(value="게시글 객체",required = true) @RequestBody @ModelAttribute BoardDto.BoardRequestDto dto, 
			BindingResult bindingresult,
			@ApiIgnore @RequestBody @ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
				
		int insertresult = 0;
		
		fvo.setImgGroup("freeBoard");
		fvo.setFileType("Board");
		
		insertresult = service.boardwrite(dto, fvo);
				
		return new ResponseDto<>(HttpStatus.OK.value(),200);
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "자유게시판 글수정 API",notes="자유게시글에서 글을 수정하는 기능입니다.")
	@PutMapping(value="/modify/{id}")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시물 번호",example="1",dataType = "Integer",paramType = "path")
	})
	public ResponseDto<?>articleupdate(
			@PathVariable(value="id",required = true)Integer boardId,
			@ApiParam(name="게시물 dto",required = true) @RequestBody @ModelAttribute BoardDto.BoardRequestDto dto,
			@ApiIgnore @ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
		
		int updateresult = 0;
		
		fvo.setImgGroup("freeBoard");
		fvo.setFileType("Board");
		
		updateresult = service.boardupdate(dto, fvo);	
		
		return new ResponseDto<>(HttpStatus.OK.value(),200);
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "자유게시판 글삭제 API",notes="자유게시글에서 글을 삭제하는 기능입니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="자유게시판 번호",example="1",dataType = "Integer",paramType = "path",required = true)
	})
	@DeleteMapping(value="/delete/{id}")
	public ResponseDto<?>articledelete(@PathVariable(value="id",required = true)Integer boardId)throws Exception{
		
		int deleteresult = 0;
		
		deleteresult = service.boarddelete(boardId);
	
		return new ResponseDto<>(HttpStatus.OK.value(),200);
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name="boardId",value="게시물 번호",example = "5003",paramType = "path",type="Integer"),
		@ApiImplicitParam(name="pwd",value="비밀번호",example = "1111",paramType = "path",type="Integer")
	})
	@ApiOperation(value = "자유게시판 비밀번호확인 API",notes="자유게시글에서 글수정시 비밀번호 확인 기능입니다.")
	@GetMapping(value="/pwcheck/{boardId}/{pwd}")
	public ResponseDto<BoardDto.BoardResponseDto> passwordCheck(
			@PathVariable(value="boardId",required = true)Integer boardId,
			@PathVariable(value="pwd",required = true)Integer passWd)throws Exception{
		BoardDto.BoardResponseDto dto = null;
		
		dto = service.passwordcheck(passWd, boardId);		
		
		return new ResponseDto<>(HttpStatus.OK.value(),dto);
	}
}
