package com.kr.coffie.common.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kr.coffie.common.file.vo.dto.FileDto;

@Mapper
public interface FileMapper {
	
	public List<FileDto.FileResponseDto>boardFileList(Integer boardId)throws Exception;
	
	public List<FileDto.FileResponseDto>noticeFileList(Integer noticeId)throws Exception;
	
	public int boardFileInsert(FileDto.FileResponseDto dto)throws Exception;
	
	public int noticeFileInsert(FileDto.FileResponseDto dto)throws Exception;

	public void boardFileDelete(Integer boardId)throws Exception;
	
	public void noticeFileDelete(Integer noticeId)throws Exception;
	
	public List<FileDto.ImageResponseDto>imagelist(Integer placeId)throws Exception;

	public int placeimageinsert(FileDto.ImageResponseDto dto)throws Exception;

	public int placeimagedelete(String fileGroupId)throws Exception;
}
