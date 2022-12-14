package com.kr.coffie.common.reply.vo.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
		@ApiModelProperty(value="댓글작성일",example="2022-09-24T11:11:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
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
		@NotBlank(message = "평점을 입력해주세요.")
		private Integer replyPoint;
		@ApiModelProperty(value="댓글좋아요",example="1",required = true)
		private Integer replyLike;
		@ApiModelProperty(value="댓글내용",example="well",required = true)
		@NotBlank(message = "내용을 입력해주세요.")
		private String replyContents;
		@ApiModelProperty(value="댓글작성자",example="well",required = true)
		@NotBlank(message = "작성자를 입력해주세요.")
		private String replyWriter;
		@ApiModelProperty(value="댓글작성일",example="2022-04-13T22:00:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
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
		@ApiModelProperty(value="댓글작성일",example="2022-10-23T22:00:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	}
}
