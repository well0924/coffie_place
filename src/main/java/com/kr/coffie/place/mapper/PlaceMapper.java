package com.kr.coffie.place.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kr.coffie.common.page.Criteria;
import com.kr.coffie.place.vo.dto.PlaceDto;

@Mapper
public interface PlaceMapper {
	
	public List<PlaceDto.PlaceResponseDto>placelist(Criteria cri)throws Exception;

	public PlaceDto.PlaceResponseDto placeDetail(Integer placeId)throws Exception;

	public int placetotal(Criteria cri)throws Exception;

	public List<Map<String,Object>>placeautocomplete(Map<String,Object>result)throws Exception;

	public List<PlaceDto.PlaceResponseDto>excellist()throws Exception;

	public List<PlaceDto.PlaceImageResponseDto>Top5CafeList()throws Exception;

	public int placeregister(PlaceDto.PlaceRequestDto dto)throws Exception;

	public int placeupdate(PlaceDto.PlaceRequestDto dto)throws Exception;

	public int placedelete(Integer placeId)throws Exception;

	public void cafeReviewRate(@Param("placeId") Integer placeId,@Param("reviewRate") Double reviewRate)throws Exception;
}
