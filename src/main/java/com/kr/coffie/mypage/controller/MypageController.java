package com.kr.coffie.mypage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.mypage.service.MypageService;
import com.kr.coffie.mypage.vo.dto.MypageDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/page/mypage/*")
public class MypageController {
	
	private final MypageService service;
	
	@GetMapping("/page/{id}")
	public ModelAndView mypage(@PathVariable("id") String userId)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		List<MypageDto.MypageResponseDto>wishlist = null;
		
		try {
		
			wishlist = service.wishlist(userId);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		mv.addObject("wishlist", wishlist);
		mv.setViewName("mypage/wishlist");
	
		return mv;
	}
	
	@GetMapping("/myarticle/{userId}")
	public ModelAndView myboard(
			@PathVariable(value="userId",required = true)String userId,
			@ModelAttribute MypageDto.MypageArticle article)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		List<MypageDto.MypageArticle>articlelist = null;
		
		try {
	
			articlelist = service.myarticle(userId);
		
		} catch (Exception e) {
		
			e.printStackTrace();
	
		}
		
		mv.addObject("mylist", articlelist);			
		mv.setViewName("mypage/myarticle");
	
		return mv;
	}
}
