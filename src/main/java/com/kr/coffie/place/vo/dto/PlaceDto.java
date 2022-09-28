package com.kr.coffie.place.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.kr.coffie.common.ecxcel.ExcelColumn;
import com.kr.coffie.common.ecxcel.ExcelFileName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class PlaceDto {
	
	@ApiModel(value="가게요청Dto",description = "가게요청에 필요한 Dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PlaceRequestDto {
		
		@ApiModelProperty(value="가게번호",example="1",required = true)
		private Integer placeId;
		@ApiModelProperty(value="가게경도",example="134.34343",required = true)
		private Double placeLng;
		@ApiModelProperty(value="가게위도",example="13.4343",required = true)
		private Double placeLat;
		@ApiModelProperty(value="가게이름",example="well",required = true)
		private String placeName;
		@ApiModelProperty(value="가게주소1",example="well",required = true)
		private String placeAddr1;
		@ApiModelProperty(value="가게주소2",example="well")
		private String placeAddr2;
		@ApiModelProperty(value="가게번호",example="02-999-3242",required = true)
		private String placePhone;
		@ApiModelProperty(value="가게시작시간",example="10:00",required = true)
		private String placeStart;
		@ApiModelProperty(value="가게종료시간",example="18:00",required = true)
		private String placeClose;
		@ApiModelProperty(value="가게등록인",example="well",required = true)
		private String placeAuthor;
		@ApiModelProperty(value="가게파일그룹아이디",example="place_g2ww23",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="가게등록일",example="2022-09-19T19:00:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	}
	
	@ApiModel(value="가게응답Dto",description = "가게응답에 필요한 Dto")
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	@ExcelFileName(fileName ="가게 목록")
	public static class PlaceResponseDto {
		
		@ApiModelProperty(value="가게번호",example="1",required = true)
		@ExcelColumn(headerName="가게번호")
		private Integer placeId;
		@ApiModelProperty(value="가게경도",example="134.34343",required = true)
		private Double placeLng;
		@ApiModelProperty(value="가게위도",example="13.4343",required = true)
		private Double placeLat;
		@ApiModelProperty(value="평점점수",example="3.343",required = true)
		@ExcelColumn(headerName="평점 점수")
		private Double reviewRate;
		@ApiModelProperty(value="가게이름",example="가게1",required = true)
		@ExcelColumn(headerName="가게이름")
		private String placeName;
		@ApiModelProperty(value="가게주소",example="서울시 강북구 수유",required = true)
		@ExcelColumn(headerName="가게주소")
		private String placeAddr1;
		@ApiModelProperty(value="가게상세주소",example="xx아파트 ",required = true)
		@ExcelColumn(headerName="가게 상세주소")
		private String placeAddr2;
		@ApiModelProperty(value="가게전화번호",example="02-904-3954",required = true)
		@ExcelColumn(headerName="가게전화번호")
		private String placePhone;
		@ApiModelProperty(value="가게시작시간",example="09:00",required = true)
		@ExcelColumn(headerName="가게시작시간")
		private String placeStart;
		@ApiModelProperty(value="가게종료시간",example="22:00",required = true)
		@ExcelColumn(headerName="가게종료시간")
		private String placeClose;
		@ApiModelProperty(value="가게등록자",example="tester",required = true)
		@ExcelColumn(headerName="가게등록자")
		private String placeAuthor;
		@ApiModelProperty(value="파일그룹아이디",example="place_d2f5fe",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="가게등록일",example="2022-04-31T10:00:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
		
	}
	
	//top5
	@ApiModel(value="top5Dto",description = "top5에 필요한 Dto")
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	public static class PlaceImageResponseDto{
		
		@ApiModelProperty(value="가게번호",example="11",required = true)
		private Integer placeId;
		@ApiModelProperty(value="가게이름",example="well",required = true)
		private String placeName;
		@ApiModelProperty(value="가게주소1",example="서울시 강북구",required = true)
		private String placeAddr1;
		@ApiModelProperty(value="가게시작시간",example="10:00",required = true)
		private String placeStart;
		@ApiModelProperty(value="가게종료시간",example="18:00",required = true)
		private String placeClose;
		@ApiModelProperty(value="가게평점",example="4.332",required = true)
		private Double reviewRate;
		@ApiModelProperty(value="파일그룹아이디",example="place_j34mr3",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="섬네일 이미지경로")
		private String thumbFileImagePath;
		@ApiModelProperty(value="메인 이미지 유무",example="1",required = true)
		private String isTitle;
		@ApiModelProperty(value="이미지경로")
		private String imgPath;
		
	}
}
