package com.kr.coffie.place.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.coffie.common.ecxcel.ExcelColumn;
import com.kr.coffie.common.ecxcel.ExcelFileName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class PlaceDto {
	
	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PlaceRequestDto {
		
		private Integer placeId;
		private Double placeLng;
		private Double placeLat;
		private String placeName;
		private String placeAddr1;
		private String placeAddr2;
		private String placePhone;
		private String placeStart;
		private String placeClose;
		private String placeAuthor;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
	}
	
	
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	@ExcelFileName(fileName ="가게 목록")
	public static class PlaceResponseDto {
		@ExcelColumn(headerName="가게번호")
		private Integer placeId;
		private Double placeLng;
		private Double placeLat;
		@ExcelColumn(headerName="평점 점수")
		private Double reviewRate;
		@ExcelColumn(headerName="가게이름")
		private String placeName;
		@ExcelColumn(headerName="가게주소")
		private String placeAddr1;
		@ExcelColumn(headerName="가게 상세주소")
		private String placeAddr2;
		@ExcelColumn(headerName="가게전화번호")
		private String placePhone;
		@ExcelColumn(headerName="가게시작시간")
		private String placeStart;
		@ExcelColumn(headerName="가게종료시간")
		private String placeClose;
		@ExcelColumn(headerName="가게등록자")
		private String placeAuthor;
		private String fileGroupId;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
		
	}
	
	//top5
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	public static class PlaceImageResponseDto{
		private Integer placeId;
		private String placeName;
		private String placeAddr1;
		private String placeStart;
		private String placeClose;
		private Double reviewRate;
		private String fileGroupId;
		private String thumbFileImagePath;
		private String isTitle;
		private String imgPath;
		
	}
}
