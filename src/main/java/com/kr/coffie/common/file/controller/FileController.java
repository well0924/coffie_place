package com.kr.coffie.common.file.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.ecxcel.ExcelService;
import com.kr.coffie.common.file.service.FileService;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.place.service.PlaceService;
import com.kr.coffie.place.vo.dto.PlaceDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/file/*")
public class FileController {
	
	private final FileService service;
	
	private final PlaceService placeservice;
	
	@GetMapping("/boarddownload")
	public ResponseEntity<Resource>boardfiledownload(@RequestParam Integer boardId)throws Exception{
		
		HttpHeaders header = new HttpHeaders();
	
		Resource res = null;
		
		try {
			//파일 다운로드
			//첨부파일 목록 가져오기.
			List<FileDto.FileResponseDto> vo = service.boardFileList(boardId);
			
			//파일 경로
			String filePath =  vo.get(0).getFilePath();
			//원본명
			String originName = vo.get(0).getOriginName();
		    //풀경로
			String fullPath = filePath ;
			 
			//파일객체 생성
			File targetFile = new File(fullPath);
			
			if(targetFile.exists()) {//파일이 존재를 했을 경우
				String mimeType = Files.probeContentType(Paths.get(targetFile.getAbsolutePath()));
				
				if(mimeType == null) {
					mimeType = "application/octet-stream";
				}
				//절대경로를 지정해주면 해당경로의 파일을 가져와 준다.
				res = new UrlResource(targetFile.toURI());
				 
				String encodingFileName = URLEncoder.encode(originName, "UTF-8").replace("+","%20");
				 
				header.set("Content-Disposition","attachment;filename="+ encodingFileName+";filename*=UTF-8''"+encodingFileName);
				header.setCacheControl("no-cache");
				header.setContentType(MediaType.parseMediaType(mimeType));
				
			}	
		} catch (Exception e) {
	
			e.printStackTrace();
	
		}
	
		return new ResponseEntity<Resource>(res,header,HttpStatus.OK);
	}

	//공지게시판 다운로드
	@GetMapping("/noticedownload")
	public ResponseEntity<Resource>noticefiledownload(
		@RequestParam Integer noticeId)throws Exception{
		
		HttpHeaders header = new HttpHeaders();

		Resource res = null;
		
		try {
			//파일 다운로드
			//첨부파일 목록 가져오기.
			List<FileDto.FileResponseDto> vo = service.noticeFileList(noticeId);
			
			//파일 경로
			String filePath =  vo.get(0).getFilePath();
			//원본명
			String originName = vo.get(0).getOriginName();
		    //풀경로
			String fullPath = filePath ;
			 
			//파일객체 생성
			File targetFile = new File(fullPath);
			
			if(targetFile.exists()) {//파일이 존재를 했을 경우
		
				String mimeType = Files.probeContentType(Paths.get(targetFile.getAbsolutePath()));
				
				if(mimeType == null) {
				
					mimeType = "application/octet-stream";
				
				}
				//절대경로를 지정해주면 해당경로의 파일을 가져와 준다.
				res = new UrlResource(targetFile.toURI());
				 
				String encodingFileName = URLEncoder.encode(originName, "UTF-8").replace("+","%20");
				 
				header.set("Content-Disposition","attachment;filename="+ encodingFileName+";filename*=UTF-8''"+encodingFileName);
	
				header.setCacheControl("no-cache");
	
				header.setContentType(MediaType.parseMediaType(mimeType));
				
			}	
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return new ResponseEntity<Resource>(res,header,HttpStatus.OK);
	}
	
	@GetMapping("/exceldown")
	public void placeExcelDown(HttpServletRequest req, HttpServletResponse res) {
		try {
		
			List<PlaceDto.PlaceResponseDto>placelist = placeservice.excellist();
	
			ExcelService<PlaceDto.PlaceResponseDto>excellist = new ExcelService<>(placelist, PlaceDto.PlaceResponseDto.class);
			
			excellist.downloadExcel(res);
			
		} catch (Exception e) {
	
			e.printStackTrace();
		
		}
	}

}
