package com.kr.coffie.config.security.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author 양경빈
 * @내용: loginfailhandler 설정 변경
 *       로그인 실패시 에러 메시지 출력
 * @일자: 2022년 10월2일      
 */
@Log4j2
@Component
public class LoginFaileHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		String errorMsg; 
		
		//BadCredentialsException: 비밀번호가 일치하지 않을경우 
		if (exception instanceof BadCredentialsException) { 
			errorMsg = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
		//InternalAuthenticationServiceException: 인증처리에 문제가 있는 경우
		} else if (exception instanceof InternalAuthenticationServiceException) {            
			errorMsg = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";        
		} else if (exception instanceof UsernameNotFoundException) {            
			errorMsg = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";        
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {            
			errorMsg = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";        } 
		else {            
			errorMsg = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";        
		}
		log.info(exception);
		errorMsg = URLEncoder.encode(errorMsg, "UTF-8");
		setDefaultFailureUrl("/page/login/loginpage?error=true&exception="+errorMsg);
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
