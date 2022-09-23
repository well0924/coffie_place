package com.kr.coffie.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kr.coffie.board.vo.dto.BoardDto;
import com.kr.coffie.common.page.Criteria;

@Mapper
public interface BoardMapper {
	
	public List<BoardDto.BoardResponseDto>boardlist(Criteria cri)throws Exception;
	
	public BoardDto.BoardResponseDto boarddetail(Integer boardId)throws Exception;
	
	public int boardwrite(BoardDto.BoardRequestDto dto)throws Exception;
	
	public int boardupdate(BoardDto.BoardRequestDto dto)throws Exception;
	
	public int boarddelete(Integer boardId)throws Exception;
	
	public void readcountup(Integer boardId)throws Exception;
	
	public int totalarticle(Criteria cri)throws Exception;
	
	public BoardDto.BoardResponseDto passwordcheck(
			@Param("passWd")Integer passWd, 
			@Param("boardId") Integer boardId)throws Exception;

}
