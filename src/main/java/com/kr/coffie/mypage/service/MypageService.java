package com.kr.coffie.mypage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kr.coffie.mypage.mapper.MypageMapper;
import com.kr.coffie.mypage.vo.dto.MypageDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MypageService {
	
	private final MypageMapper mapper;
	
	public List<MypageDto.MypageResponseDto>wishlist(@Param("userId")String userId)throws Exception{
		return mapper.wishlist(userId);
	};

	public int wishinsert(MypageDto.MypageRequestDto dto)throws Exception{
		return mapper.wishinsert(dto);
	};

	public int wishdelete(@Param("favoriteId") Integer favoriteId,@Param("userId")String userId)throws Exception{
		return mapper.wishdelete(favoriteId, userId);
	};

	public int wishcheck(@Param("userId")String userId,@Param("placeId") Integer placeId)throws Exception{
		return mapper.wishcheck(userId, placeId);
	};
	
	public List<MypageDto.MypageArticle>myarticle(@Param("userId")String userId)throws Exception{
		return mapper.myarticle(userId);
	};
	
}
