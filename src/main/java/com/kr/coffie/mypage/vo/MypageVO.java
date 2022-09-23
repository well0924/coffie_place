package com.kr.coffie.mypage.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MypageVO {
	
	private Integer favoriteId;
	
	private Integer placeId;
	
	private String userId;
	
	private String fileGroupId;
	
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
	private LocalDateTime createdAt;
}
