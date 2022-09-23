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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reply/*")
public class ReplyApiController {
	
	private final ReplyService service;
	
	@GetMapping("/{id}")
	public List<ReplyDto.ReplyResponseDto>replylist(@PathVariable("id")Integer boardId)throws Exception{
					
		List<ReplyDto.ReplyResponseDto>replylist = null;
		
		try {	
			replylist = service.replylist(boardId);	
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return replylist;
	}
	
	@GetMapping("/{id}/{re}")
	public ReplyDto.ReplyResponseDto replydetail(@PathVariable("re") Integer replyId,@PathVariable("id")Integer boardId)throws Exception{
		
		ReplyDto.ReplyResponseDto detail = null;
		
		try {
	
			detail = service.replydetail(replyId);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return detail;
	}
	
	@PostMapping(value="/write")
	public Map<String,Object>replywrite(@RequestBody ReplyDto.ReplyRequestDto param)throws Exception{
		
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
	
	@DeleteMapping("/delete/{id}")
	public Map<String,Object>replydelete(@PathVariable("id")Integer boardId)throws Exception{
		
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
	
	@GetMapping("/list/{id}/{re}")
	public ReplyDto.PlaceReplyResponseDto placereplydetail(@PathVariable(value="id",required = true)Integer boardId, @PathVariable(value="re",required = true)Integer replyId)throws Exception{

		ReplyDto.PlaceReplyResponseDto detail = null;
	
		try {

			detail = service.placereplydetail(replyId);
	
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return detail;
	}
	
	@PostMapping("/placewrite")
	public Map<String,Object>placereplywrite(@RequestBody ReplyDto.ReplyRequestDto param )throws Exception{
		
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
