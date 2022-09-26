package com.kr.coffie.notice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kr.coffie.common.file.FileUtile;
import com.kr.coffie.common.file.mapper.FileMapper;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.notice.mapper.NoticeMapper;
import com.kr.coffie.notice.vo.dto.NoticeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeService {
	
	private final NoticeMapper mapper;
	
	private final FileUtile utile;
	
	private final FileMapper filemapper;
	
	public List<NoticeDto.NoticeResponseDto> noticelist(Criteria cri) throws Exception{
		return mapper.noticelist(cri);
	};

	public NoticeDto.NoticeResponseDto noticedetail(Integer noticeId)throws Exception{
		return mapper.noticedetail(noticeId);
	};

	public int noticeinsert(NoticeDto.NoticeRequestDto dto,FileDto.FileRequestDto fvo)throws Exception{

		int result = mapper.noticeinsert(dto);
		
		List<FileDto.FileResponseDto>filelist = new ArrayList<>();
		
		if(result > 0) {
			filelist = utile.fileupload(fvo);
			
			if(filelist != null) {
				for(FileDto.FileResponseDto fileVO : filelist) {
					fileVO.setNoticeId(dto.getNoticeId());
					
					int attachresult = filemapper.noticeFileInsert(fileVO);
				}
			}
		}
		
		if(filelist == null || filelist.size() == 0) {
			result = mapper.noticeinsert(dto);
		}
		
		return result;
	};

	public int noticedelete(Integer noticeId)throws Exception{
		
		int result = mapper.noticedelete(noticeId);
		
		List<FileDto.FileResponseDto>filelist = filemapper.noticeFileList(noticeId);
		
		//첨부파일이 없는 경우에는 게시물 삭제를 실행
		if(filelist == null ||filelist.size() == 0) {
			return result;
		}

		if(filelist != null) {

			for(int i= 0; i<filelist.size(); i++) {			
			
				String filepath = filelist.get(i).getFilePath();
				
				File path = new File(filepath);
				
				//경로 삭제
				if(path.exists()) {
				
					path.delete();
				
				}
				filemapper.noticeFileDelete(noticeId);
			}		
		}
	
		return result;
	};

	public int noticeupdate(NoticeDto.NoticeRequestDto dto,FileDto.FileRequestDto fvo)throws Exception{
		
		int result = mapper.noticeupdate(dto);
		
		List<FileDto.FileResponseDto>filelists = filemapper.noticeFileList(dto.getNoticeId());
		
		if(result >0) {
			if(filelists != null) {
				for(int i=0;i<filelists.size();i++) {
					String filepath = filelists.get(i).getFilePath();
					File path = new File(filepath);
					
					if(path.exists()) {
						path.delete();
					}
					filemapper.noticeFileDelete(dto.getNoticeId());
				}
			}
			
			filelists = utile.fileupload(fvo);
			
			for(FileDto.FileResponseDto fileVO : filelists) {
				fileVO.setNoticeId(dto.getNoticeId());
				
				int attachresult = filemapper.noticeFileInsert(fileVO);
			}	
		}
		
		if(filelists == null || filelists.size() == 0) {
			return result;
		}
		
		return result;
	};

	public int noticetotalcount(Criteria cri)throws Exception{
		return mapper.noticetotalcount(cri);
	};
	
}
