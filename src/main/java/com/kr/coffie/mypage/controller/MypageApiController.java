package com.kr.coffie.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import springfox.documentation.annotations.ApiIgnore;

@Log4j2
@Api(tags = {"마이페이지 Api"} ,value ="마이페이지에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/mypage/*")
public class MypageApiController {
	
	private final MypageService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="회원아이디",required = true,dataType = "String",example="well4149",paramType = "path")
	})
	@ApiOperation(value = "위시리스트 목록 API",notes="위시리스트의 목록을 조회합니다.")
	@GetMapping("/mylist/{id}")
	public Map<String,Object> mywishlist(@PathVariable(value="id")String userId,
			@ApiIgnore
			@ModelAttribute
			@RequestBody
			MypageDto.MypageResponseDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<MypageDto.MypageResponseDto> article =null;
		
		try {
			article = service.wishlist(userId);
			
			log.info("내가쓴 글:"+article);
			result.put("mylist", article);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(),500);
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
	@ApiOperation(value = "위시리스트 추가 API",notes="위시리스트를 추가하는 api입니다.")
	@PostMapping("/wishinsert")
	public Map<String,Object>wishinsert(
			@ApiParam(value="마이리스트 dto",required = true)
			@RequestBody 
			MypageDto.MypageRequestDto dto)throws Exception{
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		int insertresult = 0;
		
		try {
			
			insertresult = service.wishinsert(dto);
			
			if(insertresult > 0) {
		
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "위시리스트 삭제 API",notes="위시리스트를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="fid",value="위시리스트 번호",example="1",paramType = "path"),
		@ApiImplicitParam(required = true,name="id",value="회원아이디",example="well4149",paramType = "path")
	})
	@DeleteMapping("/delete/{fid}/{id}")
	public Map<String,Object>wishdelete(
	@PathVariable(value="fid",required=true)
	@RequestBody
	Integer favoriteId,
	@PathVariable(value="id",required = true) 
	@RequestBody 
	String userId)throws Exception{
				
		Map<String,Object>result = new HashMap<String, Object>();
		
		int deleteresult = 0;
		
		try {
			
			result.put("favoriteId", favoriteId);

			result.put("userId", userId);
			
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "위시리스트 중복체크 API",notes="가게조회페이지에서 찜하기 버튼을 누르면 위시리스트의 중복을 체크합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,example="well419",name="id",value="회원아이디",dataType = "String",paramType = "path"),
		@ApiImplicitParam(required = true,example="1",name="fid",value="가게번호",dataType = "Integer",paramType = "path")
	})
	@PostMapping("/wishcheck/{fid}/{id}")
	public Map<String,Object> wishcheck(@PathVariable(value="id",required = true)String userId,@PathVariable(value="fid",required = true)Integer placeId,
		@ApiIgnore
		@RequestBody 
		MypageDto.MypageRequestDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		int resultcheck = 0;
		
		try {
			
			resultcheck = service.wishcheck(userId, placeId);

			if(resultcheck == 0) {//처음으로 누른 경우
				result.put("checkresult", resultcheck);
				wishinsert(dto);
			}else if(resultcheck == 1){
				result.put("checkresult", resultcheck);
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
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "내가 작성한글 API",notes="공지게시글 및 자유게시판에 작성한 글을 확인하는 api")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="회원아이디",example="well4149",dataType = "String",paramType = "path")
	})
	@GetMapping("/myarticle/{id}")
	public Map<String,Object> myarticle(@PathVariable(value="id",required = true)String userId)throws Exception{
		
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
