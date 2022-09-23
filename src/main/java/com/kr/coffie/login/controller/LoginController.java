package com.kr.coffie.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/page/login/*")
public class LoginController {
	
	private final AdminService service;
	
	@GetMapping("/loginpage")
	public ModelAndView loginpage()throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login/loginpage");
		
		return mv;
	}
	
	@GetMapping("/memberjoin")
	public ModelAndView memberjoin()throws Exception{
		ModelAndView mv = new  ModelAndView();
		
		mv.setViewName("login/memberjoin");
		
		return mv;
	}
	
	@GetMapping("/membermodify/{id}")
	public ModelAndView membermodify(@PathVariable("id")String userId)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		LoginDto.LoginResponseDto detail = null;
		
		try {
			detail = service.memeberdetail(userId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("detail", detail);
		mv.setViewName("login/membermodify");
		
		return mv;
	}
	
	@GetMapping("/tmpid")
	public ModelAndView findid()throws Exception{
	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login/searchid");
		
		return mv;
	}
	
	@GetMapping("/tmppw")
	public ModelAndView findpw()throws Exception{
	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login/TempPw");
		
		return mv;
	}
	
}
