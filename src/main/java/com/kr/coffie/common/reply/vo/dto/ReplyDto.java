package com.kr.coffie.common.reply.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ReplyDto {
	
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReplyRequestDto{
		
		private Integer replyId;	
		private Integer boardId;
		private Integer placeId;
		private Integer replyPoint;
		private Integer replyLike;
		private String replyContents;
		private String replyWriter;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	}
	
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ReplyResponseDto{

		private Integer replyId;		
		private Integer boardId;
		private Integer placeId;
		private Integer replyPoint;
		private Integer replyLike;
		private String replyContents;
		private String replyWriter;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	}
	
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PlaceReplyResponseDto{
		
		private Integer replyId;	
		private Integer placeId;
		private Integer replyPoint;
		private Integer replyLike;
		private String replyContents;
		private String replyWriter;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	}
}
