package com.kr.coffie.common.file.vo.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class FileDto {
	
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class FileRequestDto{
		
		private Integer fileId;
		private Integer boardId;
		private Integer noticeId;
		private MultipartFile[]file;
		private String filePath;
		private String imgGroup;
        private String fileType;
		private String storedName;
		private String originName;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;

	}
	
	//첨부파일 조회에 사용되는 dto
	@Getter
	@Setter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class FileResponseDto{

		private Integer fileId;
		private Integer noticeId;
		private Integer boardId;
		private String filePath;
		private String storedName;
		private String originName;
		@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
		private LocalDateTime createdAt;

	}	
		
	//이미지(에디터)업로드에 사용되는 dto
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	public static class ImageRequestDto{

		private Integer imgId;
		private Integer placeId;
		private MultipartFile[]file;
		private String fileGroupId;
		private String fileType;
		private String imgGroup;
		private String imgPath;
		private String thumbFileImagePath;//이미지 경로
		private String thumbFilePath;//이미지 물리경로
		private String originName;
		private String storedName;
		private String imgUploader;
		@Builder.Default
		private String isTitle="N";
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;

	}
		
	//이미지 조회에 사용이 되는 dto	
	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	public static class ImageResponseDto{

		private Integer imgId;
		private Integer placeId;
		private String fileGroupId;
		private String fileType;
		private String imgGroup;
		private String imgPath;
		private String thumbFileImagePath;
		private String thumbFilePath;
		private String storedName;
		private String originName;
		private String imgUploader;
		@Builder.Default
		private String isTitle="N";
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		private LocalDateTime createdAt;

	}

}
