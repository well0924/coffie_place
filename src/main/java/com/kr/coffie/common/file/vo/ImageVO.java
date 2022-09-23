package com.kr.coffie.common.file.vo;

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
public class ImageVO {
	
	//가게 이미지 및 에디터 이미지 vo
	private Integer imgId;
	private Integer placeId;
	private String fileGroupId;
	private String fileType;
	private String imgGroup;
	private String imgPath;
	private String thumbFilePath;
	private String thumbFileImagePath;
	private String storedName;
	private String originName;
	private String imgUploader;
	private char   isTitle;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;
}
