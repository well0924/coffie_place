package com.kr.coffie.notice.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class NoticeDto {
	
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class NoticeRequestDto{
		
		private Integer noticeId;
		private String noticeGroup;
		private String noticeTitle;
		private String noticeContents;
		private String noticeAuthor;
		private String noticeFixed;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	
	}
	
	@Getter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class NoticeResponseDto{
		
		private Integer noticeId;
		private String noticeGroup;
		private String noticeTitle;
		private String noticeContents;
		private String noticeAuthor;
		private String noticeFixed;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime updatedAt;
	
	}
}
