package com.kr.coffie.config.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFaileHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("	<body>");
		writer.println("		<script>");
		writer.println("			alert('아이디 또는 비밀번호가 틀렸습니다.')");
		writer.println("			location.href='/page/login/loginpage';");
		writer.println("		</script>");
		writer.println("	</body>");
		writer.println("</html>");
	}
}
