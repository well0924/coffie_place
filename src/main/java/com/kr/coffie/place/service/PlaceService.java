package com.kr.coffie.place.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kr.coffie.common.file.FileUtile;
import com.kr.coffie.common.file.mapper.FileMapper;
import com.kr.coffie.common.file.vo.dto.FileDto;
import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.place.mapper.PlaceMapper;
import com.kr.coffie.place.vo.dto.PlaceDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlaceService {
	
	private final PlaceMapper mapper;
	
	private final FileUtile utile;
	
	private final FileMapper filemapper;
	
	public List<PlaceDto.PlaceResponseDto>placelist(Criteria cri)throws Exception{
		return mapper.placelist(cri);
	};

	public PlaceDto.PlaceResponseDto placeDetail(Integer placeId)throws Exception{
		return mapper.placeDetail(placeId);
	};

	public int placetotal(Criteria cri)throws Exception{
		return mapper.placetotal(cri);
	};

	public List<Map<String,Object>>placeautocomplete(Map<String,Object>result)throws Exception{
		return mapper.placeautocomplete(result);
	};

	public List<PlaceDto.PlaceResponseDto>excellist()throws Exception{
		return mapper.excellist();
	};

	public List<PlaceDto.PlaceImageResponseDto>Top5CafeList()throws Exception{
		return mapper.Top5CafeList();
	};

	public int placeregister(PlaceDto.PlaceRequestDto dto,FileDto.ImageRequestDto imgvo)throws Exception{
		int result = mapper.placeregister(dto);
		
		List<FileDto.ImageResponseDto>imagelist = new ArrayList<>();
		FileDto.ImageResponseDto imglist = null;
		
		//이미지 업로드
		if(result > 0) {
			imagelist = utile.imagefileupload(imgvo);
		}
		
		//첨부 파일이 있는 경우
		if(imagelist != null) {
			String resize = "";
			
			for(int i =0 ; i<imagelist.size();i++) {
				
				imglist = imagelist.get(i);
				//첫번째 이미지의 경우
				if(i == 0) {
					imglist.setIsTitle("1");
					resize = utile.ResizeFile(imglist, 360, 360);
				}else {//나머지
					resize = utile.ResizeFile(imglist, 120, 120);
				}
				
				imglist.setPlaceId(dto.getPlaceId());
				imgvo.setImgGroup("coffieplace");
				imgvo.setFileType("place");
				imglist.setThumbFileImagePath(resize);
				
				int attachresult = filemapper.placeimageinsert(imglist);
				
			}
		}
		
		//파일없이 가게 작성 한 경우
		if(imagelist == null || imagelist.size() == 0) {
			result = mapper.placeregister(dto);
		}
				
		return result;
	};

	public int placeupdate(PlaceDto.PlaceRequestDto dto,FileDto.ImageRequestDto imgvo)throws Exception{
		int result = mapper.placeupdate(dto); 
		
		List<FileDto.ImageResponseDto>imagelist = null;
		FileDto.ImageResponseDto imglist = null;
		
		imagelist=filemapper.imagelist(dto.getPlaceId());
		
		if(result > 0) {
			if(imagelist != null) {
				for(int i =0;i <imagelist.size();i++) {
					//파일 경로
					String filepath = imagelist.get(i).getImgPath();
					//섬네일 경로
					String thumbpath = imagelist.get(i).getThumbFilePath();
					
					File filepaths = new File(filepath);
					File thumbpaths = new File(thumbpath);
										
					//가게 이미지경로 삭제
					if(filepaths.exists()) {
						filepaths.delete();
					}
					
					if(thumbpaths.exists()) {
						thumbpaths.delete();
					}
					
					//가게이미지 삭제
					filemapper.placeimagedelete(dto.getFileGroupId());
				}
				
			}
			//이미지 재첨부
			imagelist = utile.imagefileupload(imgvo);
			
			String resize = "";
			
			for(int i =0 ; i<imagelist.size();i++) {
				
				imglist = imagelist.get(i);
				//첫번째 이미지의 경우
				if(i == 0) {
					imglist.setIsTitle("1");
					resize = utile.ResizeFile(imglist, 360, 360);
				}else {//나머지
					resize = utile.ResizeFile(imglist, 120, 120);
				}
				
				imglist.setPlaceId(dto.getPlaceId());
				imgvo.setImgGroup("coffieplace");
				imgvo.setFileType("place");
				imglist.setThumbFileImagePath(resize);
				
				int attachresult = filemapper.placeimageinsert(imglist);
				
			}
		}else {
			//이미지 재첨부
			imagelist = utile.imagefileupload(imgvo);
			
			String resize = "";
			
			for(int i =0 ; i<imagelist.size();i++) {
				
				imglist = imagelist.get(i);

				if(i == 0) {
					imglist.setIsTitle("1");
					resize = utile.ResizeFile(imglist, 360, 360);
				}else {
					resize = utile.ResizeFile(imglist, 120, 120);
				}
				
				imglist.setPlaceId(dto.getPlaceId());
				imgvo.setImgGroup("coffieplace");
				imgvo.setFileType("place");
				imglist.setThumbFileImagePath(resize);
				
				int attachresult = filemapper.placeimageinsert(imglist);
				
			}
		}
		
		if(imagelist == null || imagelist.size() ==0) {
			
			return result;
		}
		
		return result;

		
	};

	public int placedelete(Integer placeId)throws Exception{
		
		int result = mapper.placedelete(placeId);
		
		List<FileDto.ImageResponseDto>imagelist = filemapper.imagelist(placeId);
		
		//이미지 없을 경우
		if(imagelist == null || imagelist.size() ==0) {
			return result;
		}
		
		//이미지가 있는 경우
		if(imagelist != null) {
			
			for(int i=0;i<imagelist.size();i++) {
				
				String filepath = imagelist.get(i).getImgPath();
				String thumbpath = imagelist.get(i).getThumbFilePath();
				
				File path = new File(filepath);
				File thumb = new File(thumbpath);
				
				//이미지 경로 삭제
				if(path.exists()) {
					path.delete();
				}
				//썸네일 경로 삭제
				if(thumb.exists()) {
					thumb.delete();
				}
				//이미지 삭제기능
				int attachresult=filemapper.placeimagedelete(imagelist.get(i).getFileGroupId());
				
			}
		}
		
		return result;
	};

	//가게 평점o.k
	public void cafeReviewRate(@Param("placeId") Integer placeId,@Param("reviewRate") Double reviewRate)throws Exception{
		mapper.cafeReviewRate(placeId, reviewRate);
	};
}
