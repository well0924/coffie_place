package com.kr.coffie.mypage.vo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class MypageDto {
	
	@ApiModel(value="마이페이지 요청dto",description = "마이페이지 요청에 필요한 dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MypageRequestDto{
		
		@ApiModelProperty(value="위시리스트 번호",example = "46",required = true)
		private Integer favoriteId;
		@ApiModelProperty(value="위시리스트 번호",example = "143",required = true)
		private Integer placeId;
		@ApiModelProperty(value="위시리스트 번호",example = "well4149",required = true)
		private String userId;
		@ApiModelProperty(value="위시리스트 번호",example="place_f43gk5",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="등록일",example="2022-09-28 12:34",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;
	
	}
	
	@ApiModel(value="마이페이지 응답dto",description = "마이페이지 응답에 필요한 dto")	
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MypageResponseDto{
		
		@ApiModelProperty(value="위시리스트 번호",example = "1",required = true)
		private Integer favoriteId;
		@ApiModelProperty(value="가게번호",example = "1",required = true)
		private Integer placeId;
		@ApiModelProperty(value="회원아이디",example = "well4149",required = true)
		private String userId;
		@ApiModelProperty(value="파일그룹아이디",example = "place_s3f2sv",required = true)
		private String fileGroupId;
		@ApiModelProperty(value="섬네일 이미지 경로")
		private String thumbFileImagePath;
		@ApiModelProperty(value="이미지 경로")
		private String imgPath;
		@ApiModelProperty(value="메인이미지여부",example = "1",required = true)
		private String isTitle;
		@ApiModelProperty(value="가게주소1")
		private String placeAddr1;
		@ApiModelProperty(value="가게평점",example = "1.00",required = true)
		private Double reviewRate;
		@ApiModelProperty(value="위시리스트 번호",example = "가게이름",required = true)
		private String placeName;		
		@ApiModelProperty(value="등록일",example="2022-09-29 12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	
	}
	
	@ApiModel(value="내가 작성한 글dto",description = "내가 작성한 글 확인에 쓰이는 dto")
	@Getter
	@Setter
	@ToString
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MypageArticle{
		
		@ApiModelProperty(value="글번호",example = "1",required = true)
		private Integer id;
		@ApiModelProperty(value="글제목",example = "title",required = true)
		private String title;
		@ApiModelProperty(value="글내용",example = "contnets",required = true)
		private String contents;
		@ApiModelProperty(value="글저자",example = "writer")
		private String author;
		@ApiModelProperty(value="파일그룹아이디",example="place_f43gk5")
		private String fileGroupId;
		@ApiModelProperty(value="등록일",example="2022-09-30 12:34:00",required = true)
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
		private LocalDateTime createdAt;
	
	}
}
