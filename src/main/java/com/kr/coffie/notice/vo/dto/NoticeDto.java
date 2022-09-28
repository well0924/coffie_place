package com.kr.coffie.notice.vo.dto;

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

public class NoticeDto {
	
	@ApiModel(value="공지게시판 요청 dto",description = "공지게시판 요청에 필요한 dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class NoticeRequestDto{
		
		@ApiModelProperty(value="공지게시글 번호",example="1",required = true)
		private Integer noticeId;
		@ApiModelProperty(value="공지게시글 카테고리",example="자유게시판",required = true)
		@NotBlank(message = "공지그룹을 선택해주세요.")
		private String noticeGroup;
		@ApiModelProperty(value="공지게시글 제목",example="제목",required = true)
		@NotBlank(message = "제목을 입력해주세요.")
		private String noticeTitle;
		@ApiModelProperty(value="공지게시글 내용",example="내용",required = true)
		@NotBlank(message = "내용을 입력해주세요.")
		private String noticeContents;
		@ApiModelProperty(value="공지게시글 작성자",example="작성자",required = true)
		private String noticeAuthor;
		@ApiModelProperty(value="공지게시글 고정유무",example="Y",required = true)
		@NotBlank(message = "고정여부를 골라주세요.")
		private String noticeFixed;
		@ApiModelProperty(value="공지게시글 파일그룹아이디",example="notice_g4s35x",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="공지게시글 등록일",example="2022-09-17T12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	
	}
	
	@ApiModel(value="공지게시판 응답 dto",description = "공지게시판 응답에 필요한 dto")
	@Getter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class NoticeResponseDto{
		
		@ApiModelProperty(value="공지게시글 번호",example = "1",required = true)
		private Integer noticeId;
		@ApiModelProperty(value="공지게시글 카테고리",example = "공지게시판",required = true)
		private String noticeGroup;
		@ApiModelProperty(value="공지게시글 제목",example="제목",required = true)
		private String noticeTitle;
		@ApiModelProperty(value="공지게시글 내용",example="내용",required = true)
		private String noticeContents;
		@ApiModelProperty(value="공지게시글 작성자",example="작성자",required = true)
		private String noticeAuthor;
		@ApiModelProperty(value="공지게시글 고정유무",example="Y",required = true)
		private String noticeFixed;
		@ApiModelProperty(value="공지게시글 파일그룹아이디",example="notice_g4s35x",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="글 등록일",example="2022-09-17T12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	
	}
}
