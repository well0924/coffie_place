package com.kr.coffie.common.file.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kr.coffie.common.file.mapper.FileMapper;
import com.kr.coffie.common.file.vo.dto.FileDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {
	
	private final FileMapper mapper;
	
	public List<FileDto.FileResponseDto>boardFileList(Integer boardId)throws Exception{
		return mapper.boardFileList(boardId);
	};
	
	public List<FileDto.FileResponseDto>noticeFileList(Integer noticeId)throws Exception{
		return mapper.noticeFileList(noticeId);
	};
	
	public int boardFileInsert(FileDto.FileResponseDto dto)throws Exception{
		return mapper.boardFileInsert(dto);
	};
	
	public int noticeFileInsert(FileDto.FileResponseDto dto)throws Exception{
		return mapper.noticeFileInsert(dto);
	};

	public void boardFileDelete(Integer boardId)throws Exception{
		mapper.boardFileDelete(boardId);
	};
	
	public void noticeFileDelete(Integer noticeId)throws Exception{
		mapper.noticeFileDelete(noticeId);
	};
	
	public List<FileDto.ImageResponseDto>imagelist(Integer placeId)throws Exception{
		return  mapper.imagelist(placeId);
	};

	public int placeimageinsert(FileDto.ImageResponseDto dto)throws Exception{
		return mapper.placeimageinsert(dto);
	};

	public int placeimagedelete(String fileGroupId)throws Exception{
		return mapper.placeimagedelete(fileGroupId);
	};
	
}
