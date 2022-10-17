package com.kr.coffie.board.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.board.service.BoardService;
import com.kr.coffie.board.vo.dto.BoardDto;
import com.kr.coffie.common.file.service.FileService;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/page/board/*")
public class BoardController {
	
	private final BoardService service;
	
	private final FileService fileservice;
	
	@GetMapping("/list")
	public ModelAndView boardlist(
			Criteria cri,
			@RequestParam(required = false, defaultValue = "T") String searchType,
			@RequestParam(required = false) String keyword)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		List<BoardDto.BoardResponseDto>list = null;
		
		int totallist = 0;
		
		list = service.boardlist(cri);
		totallist =service.totalarticle(cri);
		
		
		Paging paging = new Paging();
		
		paging.setCri(cri);
		paging.setTotalCount(totallist);
		paging.setKeyword(keyword);
		paging.setSearchType(searchType);
		
		mv.addObject("boardlist", list);
		mv.addObject("total", totallist);
		mv.addObject("paging", paging);
		
		mv.setViewName("board/boardlist");
		return mv;
	}
	
	@GetMapping("/detail/{id}")
	public ModelAndView DetailPage(@PathVariable("id") Integer boardId,BoardDto.BoardResponseDto vo)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		BoardDto.BoardResponseDto detail = null;
		//에디터업로드
		List<FileDto.ImageResponseDto>Imagelist = null;
		//첨부파일 업로드
		List<FileDto.FileResponseDto>fileList = null;
		
		detail = service.boarddetail(boardId);
		fileList = fileservice.boardFileList(boardId);
		Imagelist = fileservice.imagelist(boardId);
		
		//조회수 증가
		service.readcountup(boardId);
		
		mv.addObject("detail", detail);
		mv.addObject("List", Imagelist);
		mv.addObject("file", fileList);
		
		mv.setViewName("board/detailboard");
		
		return mv;
	}
	
	@GetMapping("/boardwrite")
	public ModelAndView WritePage()throws Exception{

		ModelAndView mv = new ModelAndView();

		//그룹아이디 생성
		String uuid = UUID.randomUUID().toString();
		String key = "free_"+uuid.substring(0,uuid.indexOf("-"));
		
		mv.addObject("fileGroupId", key);

		mv.setViewName("board/writeboard");

		return mv;
	}
	
	@GetMapping(value="/modifypage/{boardId}")
	public ModelAndView EditePage(@PathVariable Integer boardId,BoardDto.BoardResponseDto vo)throws Exception{

		ModelAndView mv = new ModelAndView();
		
		BoardDto.BoardResponseDto modify  = null;
		//에디터업로드
		List<FileDto.ImageResponseDto>Imagelist = null;
		//첨부파일 업로드
		List<FileDto.FileResponseDto>fileList = null;
		
		modify = service.boarddetail(boardId);
		Imagelist = fileservice.imagelist(boardId);
		fileList = fileservice.boardFileList(boardId);
	
		mv.addObject("detail", modify);
		mv.addObject("List", Imagelist);
		mv.addObject("file", fileList);

		mv.setViewName("board/boardmodify");

		return mv;
	}	
	
	
	@GetMapping(value="/pwcheck/{id}")
	public ModelAndView pwCheck(@PathVariable("id") Integer boardId , BoardDto.BoardResponseDto vo)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		BoardDto.BoardResponseDto pwd = null;
		
		pwd = service.boarddetail(boardId);
		
		mv.addObject("pwd", pwd);
		mv.setViewName("board/passwordcheck");
		
		return mv;
	}
}
