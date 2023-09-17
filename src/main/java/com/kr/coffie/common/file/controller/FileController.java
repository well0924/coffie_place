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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.coffie.common.ecxcel.ExcelService;
import com.kr.coffie.common.file.service.FileService;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.exception.dto.DownloadResponseDto;
import com.kr.coffie.place.service.PlaceService;
import com.kr.coffie.place.vo.dto.PlaceDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags = {"다운로드 api"},value="파일다운로드관련 api")
@RestController
@AllArgsConstructor
@RequestMapping("/api/file/*")
public class FileController {
	
	private final FileService service;
	
	private final PlaceService placeservice;
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "게시판 다운로드",notes = "자유게시판 조회페이지에서 파일 다운로드기능")
	@GetMapping("/boarddownload")
	public DownloadResponseDto<?>boardfiledownload(@ApiParam(required = true,value="boardId",name="게시글 번호",example="1") @RequestParam Integer boardId)throws Exception{
		
		HttpHeaders header = new HttpHeaders();
	
		Resource res = null;
		
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
		
		return new DownloadResponseDto<>(HttpStatus.OK.value(),header,res);
	}

	//공지게시판 다운로드
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "공지게시판 다운로드",notes = "공지게시판 조회페이지에서 파일 다운로드기능")
	@GetMapping("/noticedownload")
	public DownloadResponseDto<Resource>noticefiledownload(
			@ApiParam(value="noticeId",name="공지게시판 번호",required = true,example="1")
			@RequestParam Integer noticeId)throws Exception{
		
		HttpHeaders header = new HttpHeaders();

		Resource res = null;
		
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
		
		return new DownloadResponseDto<>(HttpStatus.OK.value(),header,res);
	}
	
	@ApiResponses({
        @ApiResponse(code=200, message="common ok"),
        @ApiResponse(code=400, message="bad request"),
        @ApiResponse(code=500, message="error")
	})
	@ApiOperation(value = "엑셀다운로드기능",notes = "가게목록페이지에서 어드민으로 로그인을 했을경우 엑셀목록으로 받는 기능")
	@GetMapping("/exceldown")
	public DownloadResponseDto<?>placeExcelDown(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
			List<PlaceDto.PlaceResponseDto>placelist = placeservice.excellist();
	
			ExcelService<PlaceDto.PlaceResponseDto>excellist = new ExcelService<>(placelist, PlaceDto.PlaceResponseDto.class);
			
			excellist.downloadExcel(res);
			
		return new DownloadResponseDto<>();
	}

}
