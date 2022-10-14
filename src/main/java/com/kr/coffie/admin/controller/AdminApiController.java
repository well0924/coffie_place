package com.kr.coffie.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.exception.ResponseDto;
import com.kr.coffie.login.vo.dto.LoginDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags = {"어드민 Api"} ,value="어드민페이지에 사용되는 기능 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/*")
public class AdminApiController {

	private final AdminService service;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "회원수정 API",notes="회원수정페이지에서 회원수정기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="회원아이디",example="well4149",dataType ="String",paramType = "path")
	})
	@PutMapping(value="/memberupdate/{id}")
	public ResponseDto<?> memberUpdate(@PathVariable(value="id",required = true)String userId, @ApiParam(value="dto",name="로그인Dto",required = true) @RequestBody LoginDto.LoginRequestDto dto)throws Exception{
			
		int updateresult =0;
		
		updateresult = service.memberupdate(dto);
		
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
	@ApiOperation(value = "회원삭제 API",notes="회원조회페이지에서 회원을 삭제및 탈퇴하는 기능")
	@ApiImplicitParams({
		@ApiImplicitParam(required = true,name="id",value="회원아이디",dataType = "String",paramType = "path")
	})
	@DeleteMapping(value="/memberdelete/{id}")
	public ResponseDto<?> memberDelete(@PathVariable(value="id")String userId,HttpSession session)throws Exception{
				
		int deleteresult = 0;
				
		deleteresult = service.memberdelete(userId);
			
		SecurityContextHolder.clearContext();
		session.invalidate();
		
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
	@ApiOperation(value = "회원선택 체크삭제 API",notes="어드민 페이지에서 회원목록에서 체크박스 선택삭제기능")
	@PostMapping(value="/selectdelete")
	public List<String>selectmemberdelete(@RequestBody List<String>userId)throws Exception{
	
		try {
			
			service.membercheckdelete(userId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userId;
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=401, message="unauthorize"),
        @ApiResponse(code=403, message="fobidden"),
        @ApiResponse(code=404, message="not found"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "회원자동완성검색 API",notes="어드민페이지에서 검색시 회원을 자동검색하는 기능")
	@PostMapping(value="/autocompetekeyword")
	public Map<String,Object>autocompletekeyword(@RequestParam Map<String,Object>param)throws Exception{
		
		List<Map<String,Object>>list = service.memberautocomplete(param);
		
		param.put("resultList", list);
		
		return param;
	}

}
