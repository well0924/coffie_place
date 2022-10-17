package com.kr.coffie.exception.dto;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class DownloadResponseDto<T> {
	
	private Integer status;
	private HttpHeaders headers;
	private Resource res;
}
