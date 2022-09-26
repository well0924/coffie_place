package com.kr.coffie.board.vo.dto;

import lombok.Builder;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class BoardDto {
	
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class BoardRequestDto {
		@ApiModelProperty(value="게시글번호",example="1")
		private Integer boardId;
		@ApiModelProperty(value="게시글비밀번호",example="1111")
		private Integer passWd;
		private String boardTitle;
		private String boardContents;
		private String boardAuthor;
		private String fileGroupId;
		@JsonFormat(pattern = "MM-dd HH:mm")
		private LocalDateTime createdAt;
		
	}
	
	@Setter
	@Getter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class BoardResponseDto{
		@ApiModelProperty(value="게시글번호",example="1")
		private Integer boardId;
		private String boardTitle;
		private String boardContents;
		private String boardAuthor;
		private Integer readCount;
		@ApiModelProperty(value="게시글비밀번호",example="1111")
		private Integer passWd;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime updatedAt;
		
		
	}
}
