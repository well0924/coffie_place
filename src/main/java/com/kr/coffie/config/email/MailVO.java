package com.kr.coffie.config.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MailVO {

	private String toAddress;
    private String title; 
    private String message; 
    private String fromAddress;
}
