package com.kr.coffie.common.reply.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ReplyDto {
	
	@ApiModel(value="게시글댓글 요청 dto",description = "게시글댓글 요청에 필요한 dto")
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReplyRequestDto{
		
		@ApiModelProperty(value="댓글번호",example="9",required = true)
		private Integer replyId;
		@ApiModelProperty(value="게시글번호",example="4",required = true)
		private Integer boardId;
		@ApiModelProperty(value="가게번호",example="1",required = true)
		private Integer placeId;
		@ApiModelProperty(value="가게점수",example="5",required = true)
		private Integer replyPoint;
		@ApiModelProperty(value="좋아요",example="0",required = true)
		private Integer replyLike;
		@ApiModelProperty(value="댓글내용",example="well",required = true)
		private String replyContents;
		@ApiModelProperty(value="댓글작성자",example="well",required = true)
		private String replyWriter;
		@ApiModelProperty(value="댓글작성일",example="2022-09-24 11:11",required = true)
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	}
	
	@ApiModel(value="댓글 응답 dto",description = "댓글 응답에 필요한 dto")
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReplyResponseDto{
		
		@ApiModelProperty(value="댓글번호",example="1",required = true)
		private Integer replyId;
		@ApiModelProperty(value="게시글번호",example="1",required = true)
		private Integer boardId;
		@ApiModelProperty(value="가게번호",example="22",required = true)
		private Integer placeId;
		@ApiModelProperty(value="댓글점수",example="3",required = true)
		private Integer replyPoint;
		@ApiModelProperty(value="댓글좋아요",example="1",required = true)
		private Integer replyLike;
		@ApiModelProperty(value="댓글내용",example="well",required = true)
		private String replyContents;
		@ApiModelProperty(value="댓글작성자",example="well",required = true)
		private String replyWriter;
		@ApiModelProperty(value="댓글작성일",example="2022-4-13 22:00",required = true)
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	}
	
	@ApiModel(value="게시글댓글 요청 dto",description = "게시글댓글 요청에 필요한 dto")
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PlaceReplyResponseDto{
		
		@ApiModelProperty(value="댓글번호",example="15",required = true)
		private Integer replyId;	
		@ApiModelProperty(value="가게번호",example="22",required = true)
		private Integer placeId;
		@ApiModelProperty(value="댓글평점점수",example="3",required = true)
		private Integer replyPoint;
		@ApiModelProperty(value="댓글좋아요점수",example="1",required = true)
		private Integer replyLike;
		@ApiModelProperty(value="댓글내용",example="well",required = true)
		private String replyContents;
		@ApiModelProperty(value="댓글작성자",example="well",required = true)
		private String replyWriter;
		@ApiModelProperty(value="댓글작성일",example="2022-10-23 22:00",required = true)
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	}
}
