package com.kr.coffie.board.vo.dto;

import lombok.Builder;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class BoardDto {
	
	@ApiModel(value="게시판 요청 dto",description = "자유게시판 요청에 필요한 dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class BoardRequestDto {
		
		@ApiModelProperty(value="게시글번호",example="1",required = true)
		private Integer boardId;
		@ApiModelProperty(value="게시글제목",example="title",required = true)
		private String boardTitle;
		@ApiModelProperty(value="게시글내용",example="contents",required = true)
		private String boardContents;
		@ApiModelProperty(value="게시글저자",example="writer",required = true)
		private String boardAuthor;
		@ApiModelProperty(value="게시글비밀번호",example="1111")
		private Integer passWd;
		@ApiModelProperty(value="게시글 파일그룹아이디",example="free_ge3b53",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="글 등록일",example="2022-09-21 12:34",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = Shape.STRING)
		private LocalDateTime createdAt;
		
	}
	
	@ApiModel(value = "게시판 응답 dto",description = "자유게시판 응답에 필요한 dto")
	@Setter
	@Getter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class BoardResponseDto{
		
		@ApiModelProperty(value="게시글번호",example="1",required = true)
		private Integer boardId;
		@ApiModelProperty(value="게시글제목",example="title",required = true)
		private String boardTitle;
		@ApiModelProperty(value="게시글내용",example="contents",required = true)
		private String boardContents;
		@ApiModelProperty(value="게시글저자",example="writer",required = true)
		private String boardAuthor;
		@ApiModelProperty(value="게시글 조회수",example="0",required = true)
		private Integer readCount;
		@ApiModelProperty(value="게시글비밀번호",example="1111")
		private Integer passWd;
		@ApiModelProperty(value="게시글 파일그룹아이디",example="free_ge3b53",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="글 등록일",example="2022-09-22 12:34",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm",shape = Shape.STRING)
		private LocalDateTime createdAt;		
	}
}
