package com.kr.coffie.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.mypage.service.MypageService;
import com.kr.coffie.mypage.vo.dto.MypageDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"마이페이지 Api"} ,value ="마이페이지에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/mypage/*")
public class MypageApiController {
	
	private final MypageService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "위시리스트 목록 API",notes="위시리스트의 목록을 조회합니다.")
	@GetMapping("/mylist/{id}")
	public Map<String,Object> mywishlist( 
			@ApiParam(name="userId",value="회원아이디",required = true)
			@PathVariable(value="id")String userId,
			@ApiIgnore
			@ModelAttribute MypageDto.MypageResponseDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<MypageDto.MypageResponseDto> article =null;
		
		try {
			article = service.wishlist(userId);
			
			if(article !=null) {
				result.put("mywishlist", article);
			}else if(article == null) {
				result.put("not mywishlist", article);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(),500);
		}
		
		return result;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "위시리스트 추가 API",notes="위시리스트를 추가하는 api입니다.")
	@PostMapping("/wishinsert")
	public Map<String,Object>wishinsert(
			@ApiParam
			@RequestBody MypageDto.MypageRequestDto dto)throws Exception{
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		int insertresult = 0;
		
		try {
			
			insertresult = service.wishinsert(dto);
			
			if(insertresult>0) {
		
				result.put("common o.k", 200);

			}else if(insertresult < 0) {
			
				result.put("fail",400);
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
			result.put(e.getMessage(),500);
		}
	
		return result;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "위시리스트 삭제 API",notes="위시리스트를 삭제합니다.")
	@PostMapping("/delete/{fid}/{id}")
	public Map<String,Object>wishdelete(
		@ApiParam(value="fid",name="위시리스트 번호",required = true)	
		@PathVariable(value="fid",required=true)Integer favoriteId,
		@ApiParam(value="id",name="회원아이디",required = true)
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

			}else if(deleteresult < 0) {
			
				result.put("fail", 400);
			
			}
		
		} catch (Exception e) {
		
			e.printStackTrace();
			result.put(e.getMessage(),500);
		}
	
		return result;
	}
		
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "위시리스트 중복체크 API",notes="위시리스트의 중복을 체크합니다.")
	@PostMapping("/wishcheck/{fid}/{id}")
	public Map<String,Object> wishcheck(
		@ApiParam(name="회원아이디",required = true)	
		@PathVariable(value="id",required = true)String userId,
		@ApiParam(name="가게번호",required = true)
		@PathVariable(value="fid",required = true)Integer placeId,
		@ApiIgnore
		@RequestBody MypageDto.MypageRequestDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int resultcheck = 0;
		
		try {
			
			resultcheck = service.wishcheck(userId, placeId);

			if(resultcheck == 0) {//처음으로 누른 경우
				result.put("checkresult", resultcheck);
				wishinsert(dto);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
			result.put(e.getMessage(), e);
		}

		return result;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "내가 작성한글 API",notes="공지게시글 및 자유게시판에 작성한 글을 확인하는 api")
	@GetMapping("/myarticle/{id}")
	public Map<String,Object> myarticle(
			@ApiParam(value="id",name="회원아이디",required = true)
			@PathVariable(value="id")String userId)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<MypageDto.MypageArticle> article =null;
		
		try {
			article = service.myarticle(userId);
			
			if(article !=null) {
				result.put("myarticle", article);
			}else if(article == null) {
				result.put("not myarticle", article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put(e.getMessage(),500);
		}
		
		return result;
	}
}
