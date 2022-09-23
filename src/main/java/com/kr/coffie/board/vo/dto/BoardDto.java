package com.kr.coffie.board.vo.dto;

import lombok.Builder;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardDto {
	
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class BoardRequestDto{
		
		private Integer boardId;
		private String boardTitle;
		private String boardContents;
		private String boardAuthor;
		private String passWd;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
		
	}
	
	@Setter
	@Getter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class BoardResponseDto{
		
		private Integer boardId;
		private String boardTitle;
		private String boardContents;
		private String boardAuthor;
		private Integer readCount;
		private String passWd;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime updatedAt;
	}
}
