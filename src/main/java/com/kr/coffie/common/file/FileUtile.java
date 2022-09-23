package com.kr.coffie.common.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.kr.coffie.common.file.vo.dto.FileDto;
import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.MultiStepRescaleOp;

@Component
public class FileUtile {
	
	@Value("${server.file.upload}")
	private String filePath;
	
	@Value("/istatic/images/")
	private String imgPath;
	
	//게시판 파일 업로드 o.k
	public List<FileDto.FileResponseDto>fileupload(FileDto.FileRequestDto dto) throws Exception{
		//업로드 한 파일;
		MultipartFile[] files = dto.getFile();
		
		List<FileDto.FileResponseDto>filelist = new ArrayList<>();
		
		//업로드를 한 파일의 수 만큼
		for(int i=0;i<files.length;i++) {
			
			MultipartFile file = files[i];
			
			String originFileName = file.getOriginalFilename();
			
			String ext = originFileName.substring(originFileName.lastIndexOf(".")+1);
			
			String fileName = "file_"+ (i+1)+"_"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))+"."+ext;
			
			String fullPath = filePath+dto.getImgGroup()+"\\"+dto.getFileType()+"\\"+fileName;
			
			if(originFileName != null && originFileName.trim().length() > 0) {

				File newFile = new File(fullPath);
				
				if(!newFile.exists()) {//파일이 존재하지 않는다면

					if(newFile.getParentFile().mkdirs()) {//
					
						newFile.createNewFile();
					
					}
				}
				
				file.transferTo(newFile);
				
				FileDto.FileResponseDto newFileVO = FileDto.FileResponseDto
													.builder()
													.filePath(fullPath)
													.storedName(fileName)
													.originName(originFileName)
													.createdAt(dto.getCreatedAt())
													.build();
				
					filelist.add(newFileVO);
				}
			}	
			return filelist;
		}
		
		//이미지 업로드
		public List<FileDto.ImageResponseDto>imagefileupload(FileDto.ImageRequestDto imgvo)throws Exception{
			
			MultipartFile[]files = imgvo.getFile();
			
			List<FileDto.ImageResponseDto>filelist = new ArrayList<>();
			
			for(int i = 0; i<files.length; i++) {

				MultipartFile file = files[i];
				
				String originFileName = file.getOriginalFilename();
				
				String ext = originFileName.substring(originFileName.lastIndexOf(".")+1);
				
				String fileName = "file_"+(i+1)+"_"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+"." + ext;

				String thumbName= "file_"+(i+1)+"_"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+"_thumb." + ext;			

				String fullPath = filePath + imgvo.getImgGroup()+"\\"+imgvo.getFileType()+"\\"+fileName;
				
				String localPath = imgPath + imgvo.getImgGroup() +"/"+imgvo.getFileType()+"/"+thumbName;
				
				//파일이 존재하면 진행
	            if (originFileName != null && originFileName.trim().length() > 0) {

	            	File newFile = new File(fullPath);

	                if (!newFile.exists()) {

	                	if (newFile.getParentFile().mkdirs()) {
	                    
	                		newFile.createNewFile();
	                    
	                	}
	                }
	                //파일 저장
	                file.transferTo(newFile);
	              
	                String path = filePath +imgvo.getImgGroup()+"\\"+imgvo.getFileType()+"\\thumb\\"+thumbName;

	                //db 에 저장하기 위한 객체 전환
	                FileDto.ImageResponseDto newFileVO  =
	                                            FileDto.ImageResponseDto
	                                            		.builder()                                
	                                            		.fileGroupId(imgvo.getFileGroupId())
	                                            		.imgGroup(imgvo.getImgGroup())
	                                            		.fileType(imgvo.getFileType())
	                                            		.imgPath(fullPath)
	                                            		.storedName(fileName)  
	                                                    .originName(originFileName)                                              
	                                                    .thumbFileImagePath(localPath)
	                                                    .thumbFilePath(path)
	                                                    .imgUploader("admin")
	                                                    .isTitle(imgvo.getIsTitle())
	                                                    .build();

	                filelist.add(newFileVO);

	            }

			}
			
			return filelist;
		}
		
		//이미지 리사이징
		public String ResizeFile(FileDto.ImageResponseDto dto,int width,int height) {
			
			String defaultFolder = filePath+File.separator+dto.getImgGroup()+File.separator+dto.getFileType()+File.separator;

			String originFilePath = defaultFolder+dto.getStoredName();
			
			File file = new File(originFilePath);
			
			String thumblocalPath = "";
			
			boolean resultCode = false;
			
			try {

				if(filePath != null && filePath.length() !=0) {
					
					String originFileName = file.getName();
				
					String ext = originFileName.substring(originFileName.lastIndexOf(".")+1);
					
					String thumbFileName = originFileName.substring(0,originFileName.lastIndexOf("."))+"_thumb."+ext;
					
					BufferedImage originImage = ImageIO.read(new FileInputStream(file));
					
					MultiStepRescaleOp scaleImage = new MultiStepRescaleOp(width,height);
					
					scaleImage.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
					
					BufferedImage resizeImage = scaleImage.filter(originImage,null);
					
					String fullPath = defaultFolder + "thumb"+File.separator+ thumbFileName;
					
					File out = new File(fullPath);
					
					if(!out.getParentFile().exists()) {

						out.getParentFile().mkdirs();
					
					}
					
					if(!out.exists()) {
						
						resultCode = ImageIO.write(resizeImage, ext, out);
						
						if(resultCode) {

							thumblocalPath = imgPath + dto.getImgGroup()+"/"+dto.getFileType()+"/thumb/"+out.getName();
							
						}else {
							thumblocalPath = null;

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return thumblocalPath;
		}

}
