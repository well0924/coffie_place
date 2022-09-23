package com.kr.coffie.common.file.vo;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

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
public class FileVO {
	
	private Integer fileId;
	private Integer boardId;
	private Integer noticeId;
	private String filePath;
	private MultipartFile[]file;
	private String storedName;
	private String originName;
	@JsonFormat(pattern = "yyyy-mm-dd HH:mm")
	private LocalDateTime createdAt;

}
