package com.kr.coffie.exception.aop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.kr.coffie.exception.dto.ResponseDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class AopHandler {
	
	//유효성 검사
	@Around("execution(* com.kr.coffie.**..*ApiController.*(..))")
	public Object validationCheck(ProceedingJoinPoint joinpoint)throws Throwable{
		
		String typeName = joinpoint.getSignature().getDeclaringTypeName();
	    String name = joinpoint.getSignature().getName();
	    Object[] args = joinpoint.getArgs();
		
		for(Object arg : args) {
			
			if(arg instanceof BindingResult) {
				
				BindingResult bindingResult = (BindingResult)arg;
				
				if(bindingResult.hasErrors()) {
					
					Map<String,String>errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
			
						String validationkey = String.format("valid_%s", error.getField());
						
						log.info(typeName + "." + name + "() => 필드 : " + error.getField() + ", 메세지 : " + error.getDefaultMessage());
						
						errorMap.put(validationkey, error.getDefaultMessage());
					}
					
					return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
				}
			}
		}
		return joinpoint.proceed();
	}
	
	//파라미터 확인
	@Before("execution(* com.kr.coffie.**..*Controller.*(..))")
	public void startJoin(JoinPoint jp) {
		log.info("-------------------------------------------------------------------------------");
		log.info(jp.getKind());
		log.info(jp.getSignature().toString());
		log.info(Arrays.toString(jp.getArgs()));
		log.info("-------------------------------------------------------------------------------");
	}
}
