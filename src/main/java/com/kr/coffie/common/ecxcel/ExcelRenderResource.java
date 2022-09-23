package com.kr.coffie.common.ecxcel;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExcelRenderResource {
	
	
	private final String excelFileName;
	
	private final Map<String,String> excelHeaderNames;
	
	private final List<String> dataFieldNames;
	
	public String getExcelHeaderName(String dataFieldName) {
	
		return excelHeaderNames.get(dataFieldName);
	
	}
}
