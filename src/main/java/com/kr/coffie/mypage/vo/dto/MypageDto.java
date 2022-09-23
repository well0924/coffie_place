package com.kr.coffie.mypage.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class MypageDto {
	
	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MypageRequestDto{
		
		private Integer favoriteId;
		private Integer placeId;
		private String userId;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;
	
	}
	
	
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MypageResponseDto{
		
		private Integer favoriteId;
		private Integer placeId;
		private String userId;
		private String fileGroupId;
		private String thumbFileImagePath;
		private String imgPath;
		private String isTitle;
		private String placeAddr1;
		private Double reviewRate;
		private String placeName;		
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
	
	}
	
	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MypageArticle{

		private Integer id;
		private String title;
		private String contents;
		private String author;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
	
	}
}
