package com.kr.coffie.common.reply.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kr.coffie.common.reply.mapper.ReplyMapper;
import com.kr.coffie.common.reply.vo.dto.ReplyDto;
import com.kr.coffie.place.service.PlaceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReplyService {
	
	private final ReplyMapper mapper;
	
	private final PlaceService placeservice;
	
	public List<ReplyDto.ReplyResponseDto>replylist(Integer boardId)throws Exception{
		return mapper.replylist(boardId);
	};

	public ReplyDto.ReplyResponseDto replydetail(Integer replyId)throws Exception{
		return mapper.replydetail(replyId);
	};

	public int replywrite(ReplyDto.ReplyRequestDto param)throws Exception{
		return mapper.replywrite(param);
	};

	public int replydelete(Integer replyId)throws Exception{
		return mapper.replydelete(replyId);
	};
	
	public List<ReplyDto.PlaceReplyResponseDto>placereplylist(Integer placeId)throws Exception{
		return mapper.placereplylist(placeId);
	};

	public ReplyDto.PlaceReplyResponseDto placereplydetail(Integer replyId)throws Exception{
		return mapper.placereplydetail(replyId);
	};

	public int placereplywrite(ReplyDto.ReplyRequestDto param)throws Exception{
		return mapper.placereplywrite(param);
	};

	public int placereplydelete(Integer replyId)throws Exception{
		return mapper.placereplydelete(replyId);
	};

	public int replypluslike(Integer replyId)throws Exception{
		return mapper.replypluslike(replyId);
	};

	public int replyminuslike(Integer replyId)throws Exception{
		return mapper.replyminuslike(replyId);
	};

	public int replycheck(Integer replyId)throws Exception{
		return mapper.replycheck(replyId);
	};
	
	public Double getStarAvgByPlaceId(Integer placeId)throws Exception{
		return mapper.getStarAvgByPlaceId(placeId);
	};
	
	public void cafeReviewRate(@Param("placeId") Integer placeId,@Param("reviewRate") Double reviewRate)throws Exception{
		mapper.cafeReviewRate(placeId, reviewRate);
	};
	
	public void updateStar(Integer placeId)throws Exception{
		Double starAvg = mapper.getStarAvgByPlaceId(placeId);
		placeservice.cafeReviewRate(placeId, starAvg);
	}
}
