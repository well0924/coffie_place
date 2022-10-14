package com.kr.coffie.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kr.coffie.board.mapper.BoardMapper;
import com.kr.coffie.board.vo.dto.BoardDto;
import com.kr.coffie.common.file.FileUtile;
import com.kr.coffie.common.file.mapper.FileMapper;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	
	private final BoardMapper mapper;
	
	private final FileUtile utile;
	
	private final FileMapper filemapper;
	
	public List<BoardDto.BoardResponseDto>boardlist(Criteria cri)throws Exception{
		return mapper.boardlist(cri);
	};
	
	public BoardDto.BoardResponseDto boarddetail(Integer boardId)throws Exception{
		return mapper.boarddetail(boardId);
	};
	
	public int boardwrite(BoardDto.BoardRequestDto dto,FileDto.FileRequestDto fvo)throws Exception{
		
		int result = mapper.boardwrite(dto);
		
		List<FileDto.FileResponseDto> filelist = new ArrayList<>();
		
		//첨부 파일이 없는 경우
		if(filelist == null || filelist.size() == 0){
			return result;
		}
		
		if(result > 0) {	
			
			//글작성이 되면 파일 업로드 기능이 실행.
			filelist = utile.fileupload(fvo);
			
			//첨부파일이 있는 경우
			if(filelist != null) {
				
				for(FileDto.FileResponseDto fileVO : filelist) {
				
					fileVO.setBoardId(dto.getBoardId());				
					
					int attachresult = filemapper.boardFileInsert(fileVO);	
				}
			}
			
			if(filelist == null || filelist.size() == 0) {
				return result;
			}
		}
		
		return result;
	};
	
	public int boardupdate(BoardDto.BoardRequestDto dto,FileDto.FileRequestDto fvo)throws Exception{
	    int updateresult = mapper.boardupdate(dto);
	    
	    List<FileDto.FileResponseDto> filelists = filemapper.boardFileList(dto.getBoardId());
		
		//수정을 하면 업로드기능
		if(updateresult >0) {
			//첨부파일이 있는 경우->파일 삭제
			if(filelists != null) {
				//첨부파일을 삭제->그 다음 파일을 업로드+첨부파일 인서트
				for(int i= 0; i<filelists.size(); i++) {			
					
					String filepath = filelists.get(i).getFilePath();
					
					File path = new File(filepath);
					
					//경로 삭제
					if(path.exists()) {
					
						path.delete();
					
					}
					//디비에 저장된 파일 삭제
					filemapper.boardFileDelete(dto.getBoardId());
				}
			}
			//파일 업로드
			filelists = utile.fileupload(fvo);
	
			//파일 재첨부 
			for(FileDto.FileResponseDto fileVO : filelists) {
			
				fileVO.setBoardId(dto.getBoardId());				
				
				int attachresult = filemapper.boardFileInsert(fileVO);
			}
		}
		//첨부파일이 없는 경우 
		if(filelists == null ||filelists.size() == 0) {
			//게시물 수정을 실행
			return updateresult;
		}
		
		return updateresult;
	};
	
	public int boarddelete(Integer boardId)throws Exception{
		
		int result = mapper.boarddelete(boardId);
		
		List<FileDto.FileResponseDto> filelists = filemapper.boardFileList(boardId);
		
		//첨부파일이 없는 경우에는 게시물 삭제를 실행
		if(filelists == null ||filelists.size() == 0) {
			return result;
		}

		if(filelists != null) {

			for(int i= 0; i<filelists.size(); i++) {			
			
				String filepath = filelists.get(i).getFilePath();
				
				File path = new File(filepath);
				
				//경로 삭제
				if(path.exists()) {
				
					path.delete();
				
				}				
				//디비에 저장된 파일 삭제
				filemapper.boardFileDelete(boardId);
			}
		}	
		return result;
	};
	
	public void readcountup(Integer boardId)throws Exception{
		mapper.readcountup(boardId);
	};
	
	public int totalarticle(Criteria cri)throws Exception{
		return mapper.totalarticle(cri);
	};
	
	public BoardDto.BoardResponseDto passwordcheck(@Param("passWd")Integer passWd, 
			@Param("boardId") Integer boardId)throws Exception{
		return mapper.passwordcheck(passWd, boardId);
	};

}
