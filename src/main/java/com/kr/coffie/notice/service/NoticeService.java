package com.kr.coffie.notice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.notice.mapper.NoticeMapper;
import com.kr.coffie.notice.vo.dto.NoticeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeService {
	
	private final NoticeMapper mapper;
		
	public List<NoticeDto.NoticeResponseDto> noticelist(Criteria cri) throws Exception{
		return mapper.noticelist(cri);
	};

	public NoticeDto.NoticeResponseDto noticedetail(Integer noticeId)throws Exception{
		return mapper.noticedetail(noticeId);
	};

	public int noticeinsert(NoticeDto.NoticeRequestDto dto)throws Exception{
		return mapper.noticeinsert(dto);
	};

	public int noticedelete(Integer noticeId)throws Exception{
		return mapper.noticedelete(noticeId);
	};

	public int noticeupdate(NoticeDto.NoticeRequestDto dto)throws Exception{
		return mapper.noticeupdate(dto);
	};

	public int noticetotalcount(Criteria cri)throws Exception{
		return mapper.noticetotalcount(cri);
	};
	
}
