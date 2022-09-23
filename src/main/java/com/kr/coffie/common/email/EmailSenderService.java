package com.kr.coffie.common.email;

import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kr.coffie.login.vo.dto.LoginDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailSenderService {
	
	private JavaMailSender mailsender;
	private static final String FROM_ADDRESS = "rayman0924@naver.com";
	private static final String JOIN_TITLE = "회원가입 인증 이메일입니다.";
	
	public EmailSenderService(JavaMailSender mailsender) {
		this.mailsender = mailsender;
	}
	
	//회원가입 이메일 인증
	public String mailSender(LoginDto.LoginRequestDto dto)throws Exception{
		
		String tmpNumber = "";
		
		try {
			
			EmailHandler mailhandler = new EmailHandler(mailsender);
			
			Random random = new Random();
			
			int checknum = random.nextInt(8888)+1111;
			
			String contents = 
					"홈페이지를 방문해주셔서 감사합니다." +
					"<br><br>" + 
					"인증 번호는 "+checknum+"입니다." + "<br>" + 
					"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
			
			mailhandler.setTo(dto.getUserEmail());
			
			mailhandler.setFrom(EmailSenderService.FROM_ADDRESS);
			
			mailhandler.setSubject(EmailSenderService.JOIN_TITLE);
			
			mailhandler.setText(contents, true);
			
			mailhandler.send();
			
			tmpNumber = Integer.toString(checknum);
			
			log.info("인증번호:"+checknum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpNumber;
	}
	
}
