package com.kr.coffie.admin.controller;

import org.springframework.stereotype.Controller;

import com.kr.coffie.admin.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {
	
	private final AdminService service;
	
	
}
