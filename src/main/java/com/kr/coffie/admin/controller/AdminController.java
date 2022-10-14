package com.kr.coffie.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.admin.service.AdminService;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/page/admin/*")
public class AdminController {
	
	private final AdminService service;
	
	@GetMapping("/adminlist")
	public ModelAndView aminlist(Criteria cri,
			@RequestParam(required = false, defaultValue = "I") String searchType,
			@RequestParam(required = false) String keyword)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		List<LoginDto.LoginResponseDto>list =null;
		int totalmember = 0;
		
		list = service.memberlist(cri);
		totalmember = service.totalmember(cri);
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(totalmember);
		paging.setKeyword(keyword);
		paging.setSearchType(searchType);
		
		mv.addObject("paging", paging);
		mv.addObject("memberlist", list);
		mv.addObject("totalmember", totalmember);
		
		mv.setViewName("admin/adminlist");
		
		return mv;
	}
	
	
}
