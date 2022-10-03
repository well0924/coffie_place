package com.kr.coffie.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/page/login/*")
public class LoginController {
	
	private final AdminService service;
	
	@GetMapping("/loginpage")
	public ModelAndView loginpage(
			@RequestParam(value="error",required = false) String error, 
			@RequestParam(value="exception",required = false) String exception)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("error", error);
		mv.addObject("exception", exception);
		
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
			detail = service.memberdetail(userId);
			log.info("??:"+detail);
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
	
	@GetMapping("/pwdchange")
	public ModelAndView findpw()throws Exception{
	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("login/TempPw");
		
		return mv;
	}
	
}
