package com.kr.coffie.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kr.coffie.board.service.BoardService;
import com.kr.coffie.board.vo.dto.BoardDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.common.page.Paging;
import com.kr.coffie.notice.service.NoticeService;
import com.kr.coffie.notice.vo.dto.NoticeDto;
import com.kr.coffie.place.service.PlaceService;
import com.kr.coffie.place.vo.dto.PlaceDto;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/page/main/*")
public class MainController {
	
	private final BoardService boardservice;
	
	private final NoticeService noticeservice;
	
	private final PlaceService placeservice;
	
	@GetMapping("/mainpage")
	public ModelAndView mainpage(Criteria cri)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<BoardDto.BoardResponseDto>board = null;
		
		List<NoticeDto.NoticeResponseDto>notice = null;
		
		List<PlaceDto.PlaceImageResponseDto>placelist = null;
		
		int total=0;
		
		try {
			
			board = boardservice.boardlist(cri);
			notice = noticeservice.noticelist(cri);
			placelist = placeservice.Top5CafeList();
			
			total = boardservice.totalarticle(cri);
			total = noticeservice.noticetotalcount(cri);
			
			log.info("게시판:"+board);
			log.info("공지:"+notice);
			log.info("이미지:"+placelist);
			Paging paging = new Paging();
			paging.setCri(cri);
			paging.setTotalCount(total);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("top5", placelist);
		mv.addObject("boardlist", board);
		mv.addObject("noticelist", notice);
	
		mv.setViewName("main/main");
		
		return mv;
	}

}
