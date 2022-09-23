package com.kr.coffie.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.notice.vo.dto.NoticeDto;


@Mapper
public interface NoticeMapper {
	
	public List<NoticeDto.NoticeResponseDto> noticelist(Criteria cri) throws Exception;

	public NoticeDto.NoticeResponseDto noticedetail(Integer noticeId)throws Exception;

	public int noticeinsert(NoticeDto.NoticeRequestDto dto)throws Exception;

	public int noticedelete(Integer noticeId)throws Exception;

	public int noticeupdate(NoticeDto.NoticeRequestDto dto)throws Exception;

	public int noticetotalcount(Criteria cri)throws Exception;
}
