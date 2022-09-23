package com.kr.coffie.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kr.coffie.mypage.vo.dto.MypageDto;

@Mapper
public interface MypageMapper {
	
	public List<MypageDto.MypageResponseDto>wishlist(@Param("userId")String userId)throws Exception;

	public int wishinsert(MypageDto.MypageRequestDto dto)throws Exception;

	public int wishdelete(@Param("favoriteId") Integer favoriteId,@Param("userId")String userId)throws Exception;

	public int wishcheck(@Param("userId")String userId,@Param("placeId") Integer placeId)throws Exception;
	
	public List<MypageDto.MypageArticle>myarticle(@Param("userId")String userId)throws Exception;
}
