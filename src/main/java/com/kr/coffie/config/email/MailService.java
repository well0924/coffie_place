package com.kr.coffie.config.email;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSenderImpl mailsender;
	
	private int authNumber;
	
	//랜덤인증번호 생성
	public void makerandomNumber() {
		Random rm = new Random();
		
		int checknum = rm.nextInt(8888)+1111;
		
		System.out.println("인증번호"+checknum);
		
		authNumber = checknum;
	}
	
	//인증메일 생성
	public String joinConfirmEmail(String userEmail) {
		
		makerandomNumber();
	
		String from = "well414965@gmail.com";
		String toMail =  userEmail;
		String title = "회원 인증 이메일 입니다.";
		String content = "회원 가입 인증메일입니다."
						+"<br><br>"+
						"인증번호는"+authNumber+"입니다."+"<br>"
						+"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
	
		mailSend(from, toMail, title, content);
	
		return Integer.toString(authNumber);
	}
	
	//이메일 전송
	public void mailSend(String form,String toMail,String title,String content) {
		
		MimeMessage message = mailsender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message);
			
			helper.setFrom(form);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content);
		
			mailsender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
