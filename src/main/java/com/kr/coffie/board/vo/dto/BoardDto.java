package com.kr.coffie.board.vo.dto;


import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardDto {
	
	@ApiModel(value="게시판 요청 dto",description = "자유게시판 요청에 필요한 dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class BoardRequestDto {
		
		@ApiModelProperty(value="게시글번호",example="1")
		private Integer boardId;
		
		@ApiModelProperty(value="게시글제목",example="title",required = true)
		@NotBlank(message = "제목을 입력해주세요.")
		private String boardTitle;
		
		@ApiModelProperty(value="게시글내용",example="contents",required = true)
		@NotBlank(message = "내용을 입력해주세요.")
		private String boardContents;
		
		@ApiModelProperty(value="게시글저자",example="writer",required = true)
		private String boardAuthor;
		
		@ApiModelProperty(value="게시글 조회수",example="0",required = true)
		private Integer readCount;
		
		@ApiModelProperty(value="게시글비밀번호",example="1111")
		private Integer passWd;
		
		@ApiModelProperty(value="게시글 파일그룹아이디",example="free_ge3b53",required = true)
		private String fileGroupId;
		
		@ApiModelProperty(value="글 등록일",example="2022-09-21 12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
		
	}
	
	@ApiModel(value = "게시판 응답 dto",description = "자유게시판 응답에 필요한 dto")
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
		
		@ApiModelProperty(value="글 등록일",example="2022-09-22T12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape=Shape.STRING)
		private LocalDateTime createdAt;		
	}
}
