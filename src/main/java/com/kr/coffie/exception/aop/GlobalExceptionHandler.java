package com.kr.coffie.exception.aop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kr.coffie.exception.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= Exception.class)
	public ResponseDto<?>IllegalArgumentException(Exception e)throws Exception{
		
		return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
	
}
