package com.kr.coffie.notice.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeVO {
	
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
