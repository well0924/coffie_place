package com.kr.coffie.place.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.place.service.PlaceService;
import com.kr.coffie.place.vo.dto.PlaceDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"가게기능 api"},value="가게기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/place/*")
public class PlaceApiController {
	
	private final PlaceService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "가게목록",notes = "가게목록페이지에서 가게목록 보여주기.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="keyword",value="검색어",example="test",dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page",value="페이지",example="1",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="perPageNum",value="페이지번호",example="5",dataType = "Integer",paramType = "query"),
		@ApiImplicitParam(name="searchType",value="검색타입",example="T",dataType = "String",paramType = "query")
	})
	@GetMapping(value="/placelist")
	public Map<String,Object>placelist(
			@ApiParam(name="페이징 및 검색 객체",required = true)
			Criteria cri)throws Exception{
		
		Map<String,Object>result = new HashMap<String,Object>();
		
		List<PlaceDto.PlaceResponseDto>placelist =null;
		
		int totalplace = 0;
		
		try {
			placelist = service.placelist(cri);
			totalplace = service.placetotal(cri);
			
			if(placelist != null) {
			
				result.put("placelist", placelist);
				result.put("totalplace",totalplace);
			}else {
			
				result.put("bad request", 400);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		}
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(totalplace);
		
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
	@ApiOperation(value = "가게단일 조회",notes = "가게목록페이지에서 가게상세조회하기.")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="가게번호",example="1",dataType = "Integer",paramType = "path")
	})
	@GetMapping("/placedetail/{id}")
	public Map<String,Object>placedetail(@PathVariable(value="id")Integer placeId,
			@ApiIgnore
			PlaceDto.PlaceResponseDto dto)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		try {
			
			dto = service.placeDetail(placeId);
			
			if(dto != null) {
				result.put("placeDetail", dto);
			}else {
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
	@ApiOperation(value = "가게자동완성검색",notes = "가게목록페이지에서 가게 자동완성검색기능")
	@PostMapping("/autocompletekeyword")
	public Map<String,Object>placeautocompletekeyword(@RequestParam Map<String,Object>param)throws Exception{
		
		List<Map<String,Object>>list = service.placeautocomplete(param);
		
		param.put("resultList", list);
		
		return param;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "가게등록기능",notes = "가게등록페이지에서 가게등록기능")
	@PostMapping("/placeregister")
	public Map<String,Object>placeregister(
		@ApiParam(name="가게 dto",required = true)	
		@RequestBody
		@ModelAttribute PlaceDto.PlaceRequestDto dto,
		@ApiParam(name="파일 dto",required = true)
		@ModelAttribute FileDto.ImageRequestDto imgvo)throws Exception{
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		int registerresult = 0;
		
		try {
	
			dto.setPlaceAuthor("admin");
			
			imgvo.setImgGroup("coffieplace");
			imgvo.setFileType("place");
			
			registerresult = service.placeregister(dto,imgvo);
			
			if(registerresult > 0 ) {
			
				result.put("common o.k", 200);
			
			}else if( registerresult < 0) {
			
				result.put("fail", 400);
			
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
	@ApiOperation(value = "가게등록기능",notes = "가게등록페이지에서 가게등록기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="가게번호",example="1",dataType = "Integer",paramType = "path")
	})
	@PutMapping("/placeupdate/{id}")
	public Map<String,Object>placeupdate(@PathVariable(value="id",required = true)Integer placeId,
			@ApiParam(name="가게 dto")
			@ModelAttribute PlaceDto.PlaceRequestDto dto,
			@ApiIgnore
			@ModelAttribute FileDto.ImageRequestDto imgvo)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		int updateresult = 0;
		
		try {
	
			dto.setPlaceAuthor("admin");
			
			imgvo.setImgGroup("coffieplace");
			imgvo.setFileType("place");
			
			updateresult = service.placeupdate(dto,imgvo);
			
			if(updateresult>0) {
			
				result.put("common o.k", 200);
			
			}else if(updateresult < 0) {
			
				result.put("fail", 400);
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
	@ApiOperation(value = "가게삭제기능",notes = "가게수정페이지에서 삭제기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="가게번호",example="10",dataType = "Integer",paramType = "path")
	})
	@DeleteMapping("/placedelete/{id}")
	public Map<String,Object>placedelete(@PathVariable(value="id",required = true)Integer placeId)throws Exception{
		
		Map<String,Object>result = new HashMap<String, Object>();
		
		int deleteresult = 0;
		
		try {
			
			deleteresult = service.placedelete(placeId);
			
			if(deleteresult > 0) {
	
				result.put("common o.k", 200);
			
			}else if( deleteresult < 0) {
			
				result.put("fail", 400);
			
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result.put(e.getMessage(), 500);
		
		}

		return result;
	}

}
