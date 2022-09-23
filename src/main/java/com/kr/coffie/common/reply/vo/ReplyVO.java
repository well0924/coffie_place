package com.kr.coffie.common.reply.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReplyVO {
	
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
