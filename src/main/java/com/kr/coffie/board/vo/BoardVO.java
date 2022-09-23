package com.kr.coffie.board.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardVO {
	
	private Integer boardId;
	private String boardTitle;
	private String boardContents;
	private String boardAuthor;
	private Integer readCount;
	private String passWd;
	private String fileGroupId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;
}
