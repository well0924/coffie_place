package com.kr.coffie.place.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.common.file.service.FileService;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.place.service.PlaceService;
import com.kr.coffie.place.vo.dto.PlaceDto;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/page/place/*")
public class PlaceController {
	
	private final PlaceService service;
	
	private final FileService fileservice;
	
	@GetMapping("/placelist")
	public ModelAndView placelist( Criteria cri,
			@RequestParam(required = false, defaultValue = "CN") String searchType,
			@RequestParam(required = false) String keyword)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		List<PlaceDto.PlaceResponseDto>placelist = null;
		
		int totalsum = 0;
		
		try {
			
			placelist = service.placelist(cri);
			totalsum = service.placetotal(cri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Paging paging = new Paging();
		
		paging.setCri(cri);
		paging.setTotalCount(totalsum);
		paging.setKeyword(keyword);
		paging.setSearchType(searchType);
		
		mv.addObject("placelist", placelist);
		mv.addObject("total", totalsum);
		mv.addObject("paging", paging);
		
		mv.setViewName("place/placelist");
		
		return mv;
	}

	@GetMapping("/placedetail/{id}")
	public ModelAndView placedetail(@PathVariable("id")Integer placeId)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		PlaceDto.PlaceResponseDto place = null;
		
		List<FileDto.ImageResponseDto>imagelist = null;
	
		try {
			
			imagelist = fileservice.imagelist(placeId);
			
			place = service.placeDetail(placeId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("imagelist", imagelist);
		mv.addObject("detail", place);
		
		mv.setViewName("place/placedetail");
		return mv;
	}
	
	//가게 등록 페이지
	@GetMapping("/placeregister")
	public ModelAndView placeregister()throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("place/placeregister");
		
		return mv;
	}
	
	//가게 수정 페이지
	@GetMapping("/placemodify/{id}")
	public ModelAndView placemodify(@PathVariable("id")Integer placeId,PlaceDto.PlaceResponseDto dto)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		PlaceDto.PlaceResponseDto detail = null;
		List<FileDto.ImageResponseDto>imagelist = null;
		
		try {
			detail = service.placeDetail(placeId);
			imagelist = fileservice.imagelist(placeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("detail", detail);
		mv.addObject("imagelist", imagelist);
		mv.setViewName("place/placemodify");
		
		return mv;
	}
}
