package com.kr.coffie.notice.controller;

import java.util.List;
import java.util.UUID;

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
import com.kr.coffie.notice.service.NoticeService;
import com.kr.coffie.notice.vo.dto.NoticeDto;
import com.kr.coffie.notice.vo.dto.NoticeDto.NoticeResponseDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/page/notice/*")
public class NoticeController {
	
	private final NoticeService service;
	
	private final FileService fileservice;
	
	@GetMapping("/noticelist")
	public ModelAndView NoticeList(Criteria cri,
			@RequestParam(required = false, defaultValue = "T") String searchType,
			@RequestParam(required = false) String keyword)throws Exception{

		ModelAndView mv = new ModelAndView();

		List<NoticeDto.NoticeResponseDto>list = null;

		int articlesum = 0;
		
		try {

			list = service.noticelist(cri);
			articlesum = service.noticetotalcount(cri);
			log.info(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(articlesum);
		paging.setKeyword(keyword);
	
		mv.addObject("paging", paging);
		mv.addObject("noticeList", list);
		mv.addObject("totalCount", articlesum);
		
		mv.setViewName("notice/noticelist");
		return mv;
	}
	
	@GetMapping("/noticeinsert")
	public ModelAndView NoticeInsert()throws Exception{
	
		ModelAndView mv = new ModelAndView();
		
		//그룹아이디 생성
		String uuid = UUID.randomUUID().toString();
		String key = "free_"+uuid.substring(0,uuid.indexOf("-"));
		
		mv.addObject("fileGroupId", key);
		mv.setViewName("notice/noticewrite");
		
		return mv;
	}
	
	@GetMapping("/noticedetail/{id}")
	public ModelAndView  NoticeDetail(@PathVariable(value = "id") Integer noticeId, NoticeResponseDto vo)throws Exception{
	
		ModelAndView mv = new ModelAndView();
	
		NoticeDto.NoticeResponseDto noticeBoard = null;
		
		//첨부파일 업로드
		List<FileDto.FileResponseDto>fileList = null;
		
		try {
			noticeBoard = service.noticedetail(noticeId);
			fileList =fileservice.noticeFileList(noticeId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("filelist", fileList);
		mv.addObject("detail", noticeBoard);
		
		mv.setViewName("notice/noticedetail");
	
		return mv;
	}
	
	@GetMapping("/noticemodify/{id}")
	public ModelAndView NoticeModify(@PathVariable(value="id") Integer noticeId, NoticeDto.NoticeResponseDto vo)throws Exception{
	
		ModelAndView mv = new ModelAndView();
		
		NoticeDto.NoticeResponseDto noticeBoard = null;
		
		try {
			noticeBoard = service.noticedetail(noticeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("detail", noticeBoard);
		mv.setViewName("notice/noticemodify");
		
		return mv;
	}

}
