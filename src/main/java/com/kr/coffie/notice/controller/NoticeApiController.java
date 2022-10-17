package com.kr.coffie.notice.controller;

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

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.exception.dto.ResponseDto;
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
	public ResponseDto<?>noticearticelist(Criteria cri)throws Exception{
			
		List<NoticeDto.NoticeResponseDto>list = null;
	
		int totallist = 0;
			
		list = service.noticelist(cri);			
		totallist = service.noticetotalcount(cri);
		
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
	public ResponseDto<NoticeDto.NoticeResponseDto> boarddetail(@PathVariable(value="id",required = true)Integer noticeId)throws Exception{
			
		NoticeDto.NoticeResponseDto dto = null;
		
		dto = service.noticedetail(noticeId);		
		
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
	@ApiOperation(value = "공지게시판 작성 API",notes="공지게시판에서 글을 작성합니다. 사용은 어드민으로 로그인을 해야 사용할 수 있습니다.")
	@PostMapping(value="/write")
	public ResponseDto<?> noticearticleinsert(
			@ApiParam(name="공지게시판에 사용되는 dto",required = true) @Valid @RequestBody @ModelAttribute NoticeDto.NoticeRequestDto dto,
			BindingResult bindingresult,
			@ApiParam(hidden = true) @RequestBody @ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
			
		int insertresult = 0;
		
		fvo.setImgGroup("noticeBoard");
		fvo.setFileType("Board");
			
		insertresult = service.noticeinsert(dto,fvo);	
			
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
	@ApiOperation(value = "공지게시판 수정 API",notes="공지게시판에서 글을 수정합니다. 수정은 어드민으로 로그인이 되었을때 사용을 할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시판 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@PutMapping(value="/update/{id}")
	public ResponseDto<?> noticearticleupdate(
			@PathVariable(value="id",required = true)Integer boardId,
			@ApiParam(name="공지게시판dto",required = true) @Valid @RequestBody @ModelAttribute NoticeDto.NoticeRequestDto dto,
			@ModelAttribute FileDto.FileRequestDto fvo)throws Exception{
				
		int updateresult = 0;
		
		fvo.setImgGroup("noticeBoard");
		fvo.setFileType("Board");
			
		updateresult = service.noticeupdate(dto,fvo);
			
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
	@ApiOperation(value = "공지게시판 삭제 API",notes="공지게시판에서 글을 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="공지게시글 번호",example="1",dataType = "Intger",paramType = "path")
	})
	@DeleteMapping(value="/delete/{id}")
	public ResponseDto<?> noticearticledelete(@PathVariable(value="id",required = true)Integer boardId)throws Exception{
				
		int deleteresult = 0;
		
		deleteresult = service.noticedelete(boardId);
		
		return new ResponseDto<>(HttpStatus.OK.value(),200);
	}

}
