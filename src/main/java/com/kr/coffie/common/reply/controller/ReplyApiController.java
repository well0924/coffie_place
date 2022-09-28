package com.kr.coffie.common.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.reply.service.ReplyService;
import com.kr.coffie.common.reply.vo.dto.ReplyDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags = {"댓글 api"},value="마이페이지에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/reply/*")
public class ReplyApiController {
	
	private final ReplyService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@ApiOperation(value = "게시판 댓글 목록",notes = "자유게시판 목록에서 댓글 목록")
	@GetMapping("/{id}")
	public List<ReplyDto.ReplyResponseDto>replylist( @PathVariable(value="id",required = true)Integer boardId)throws Exception{
					
		List<ReplyDto.ReplyResponseDto>replylist = null;
		
		try {	
			replylist = service.replylist(boardId);	
			
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return replylist;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "게시판 댓글 조회",notes = "자유게시판 목록에서 댓글 단일조회")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시글 번호",example="1",dataType = "Integer",paramType = "path"),
		@ApiImplicitParam(required = true,name="re",value="댓글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@GetMapping("/{id}/{re}")
	public ReplyDto.ReplyResponseDto replydetail(@PathVariable(value="re") Integer replyId,@PathVariable(value="id")Integer boardId)throws Exception{
		
		ReplyDto.ReplyResponseDto detail = null;
		
		try {
	
			detail = service.replydetail(replyId);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return detail;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "게시판 댓글 작성",notes = "자유게시판 목록에서 댓글 작성")
	@PostMapping(value="/write")
	public Map<String,Object>replywrite(
			@ApiParam(name="댓글Dto",required = true)
			@RequestBody ReplyDto.ReplyRequestDto param)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int replyresult = 0;
		
		try {
			
			replyresult = service.replywrite(param);
			
			if(replyresult >0) {
	
				result.put("common o.k", 200);
			
			}else if(replyresult <0) {
			
				result.put("bad request", 400);
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put("error", e.getMessage());
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
	@ApiOperation(value = "게시판 댓글 삭제",notes = "자유게시판 목록에서 댓글 삭제기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@DeleteMapping("/delete/{id}")
	public Map<String,Object>replydelete(@PathVariable(value="id")Integer boardId)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int replyresult = 0;
		
		try {
			
			replyresult = service.replydelete(boardId);
			
			if(replyresult >0) {
	
				result.put("common o.k", 200);
		
			}else if(replyresult <0) {
	
				result.put("bad request", 400);
	
			}
		} catch (Exception e) {
	
			e.printStackTrace();
			result.put("error", e.getMessage());
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
	@ApiOperation(value = "가게댓글목록",notes = "가게조회페이지에서 댓글목록")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@GetMapping("/list/{id}")
	public List<ReplyDto.PlaceReplyResponseDto>placereplylist(@PathVariable("id")Integer placeId)throws Exception{
				
		List<ReplyDto.PlaceReplyResponseDto>replylist = null;
		
		try {
	
			replylist = service.placereplylist(placeId);
	
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	
		return replylist;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "가게댓글 단일조회",notes = "가게조회페이지에서 댓글 단일조회")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시글 번호",example="1",dataType = "Integer",paramType = "path"),
		@ApiImplicitParam(required = true,name="re",value="댓글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@GetMapping("/list/{id}/{re}")
	public ReplyDto.PlaceReplyResponseDto placereplydetail(@PathVariable(value="id",required = true)Integer placeId,@PathVariable(value="re",required = true)Integer replyId)throws Exception{

		ReplyDto.PlaceReplyResponseDto detail = null;
	
		try {

			detail = service.placereplydetail(replyId);
	
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return detail;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "가게댓글 작성",notes = "가게조회페이지에서 댓글작성")
	@PostMapping("/placewrite")
	public Map<String,Object>placereplywrite(
			@ApiParam(name="가게댓글Dto",required = true)
			@RequestBody ReplyDto.ReplyRequestDto param)throws Exception{
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		int placereplyresult = 0;
		
		try {
			
			placereplyresult = service.placereplywrite(param);
			//평점 계산
			service.updateStar(param.getPlaceId());
			
			if(placereplyresult > 0) {
	
				result.put("common o.k",200);
			
			}else {
			
				result.put("bad request",400);
		
			}
		} catch (Exception e) {
		
			e.printStackTrace();
			result.put("error",500);
		
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
	@ApiOperation(value = "가게댓글 삭제",notes = "가게조회페이지에서 댓글삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="게시글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@DeleteMapping("/placedelete/{id}")
	public Map<String,Object>placereplydelete(@PathVariable(value="id",required = true)Integer replyId)throws Exception{
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		ReplyDto.PlaceReplyResponseDto dto = null;
		
		int placereplyresult = 0;
		
		try {
			
			dto = service.placereplydetail(replyId);
			
			placereplyresult = service.placereplydelete(replyId);
			//평점 계산
			service.updateStar(dto.getReplyId());
			
			if(placereplyresult > 0) {

				result.put("common o.k",200);

			}else {

				result.put("bad request",400);
			}

		} catch (Exception e) {

			e.printStackTrace();
			result.put("error",500);

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
	@ApiOperation(value = "가게댓글좋아요 중복체크",notes = "가게조회페이지에서 댓글좋아요 중복체크")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="댓글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@PostMapping("/likecheck/{id}")
	public int likeCheck(@PathVariable(value="id",required = true)Integer replyId)throws Exception{

		int CheckResult = 0;
		
		try {
			CheckResult = service.replycheck(replyId);

			if(CheckResult == 0 ) {

				likeUp(replyId);

			}else if( CheckResult == 1) {

				likedown(replyId);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return CheckResult;
	}

	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "가게댓글좋아요 +1",notes = "가게조회페이지에서 댓글좋아요 증가기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="re",value="댓글 번호",example="1",dataType = "Integer",paramType = "path")
	})
	@PostMapping("/likeup/{id}")
	public int likeUp(@PathVariable(value="id",required = true)Integer replyId)throws Exception{
		
		int likeResult = 0;
		
		try {
	
			likeResult = service.replypluslike(replyId);

		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return likeResult;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "가게댓글좋아요 -1",notes = "가게조회페이지에서 댓글좋아요 감소기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="가게댓글번호",example="1",dataType = "Integer",paramType = "path")
	})
	@PostMapping("/likedown/{id}")
	public int likedown(@PathVariable(value="id",required = true)Integer replyId)throws Exception{
		int likeResult = 0;
		
		try {
		
			likeResult = service.replyminuslike(replyId);

		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return likeResult;
	}
}
