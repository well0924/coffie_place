package com.kr.coffie.common.reply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kr.coffie.common.reply.vo.dto.ReplyDto;

@Mapper
public interface ReplyMapper {
	
	public List<ReplyDto.ReplyResponseDto>replylist(Integer boardId)throws Exception;

	public ReplyDto.ReplyResponseDto replydetail(Integer replyId)throws Exception;

	public int replywrite(ReplyDto.ReplyRequestDto param)throws Exception;

	public int replydelete(Integer replyId)throws Exception;
	
	public List<ReplyDto.PlaceReplyResponseDto>placereplylist(Integer placeId)throws Exception;

	public ReplyDto.PlaceReplyResponseDto placereplydetail(Integer replyId)throws Exception;

	public int placereplywrite(ReplyDto.ReplyRequestDto param)throws Exception;

	public int placereplydelete(Integer replyId)throws Exception;

	public int replypluslike(Integer replyId)throws Exception;

	public int replyminuslike(Integer replyId)throws Exception;

	public int replycheck(Integer replyId)throws Exception;

	public Double getStarAvgByPlaceId(Integer placeId)throws Exception;
	
	public void cafeReviewRate(@Param("placeId") Integer placeId,@Param("reviewRate") Double reviewRate)throws Exception;

}
