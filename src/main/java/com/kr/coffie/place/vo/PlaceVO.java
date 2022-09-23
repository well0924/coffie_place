package com.kr.coffie.place.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceVO {

	private Integer placeId;
	private Double placeLng;
	private Double placeLat;
	private Double reviewRate;
	private String placeName;
	private String placeAddr1;
	private String placeAddr2;
	private String placePhone;
	private String placeAuthor;
	private String placeStart;
	private String placeClose;
	private String fileGroupId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;
}
