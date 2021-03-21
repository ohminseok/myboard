package com.myboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myboard.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardDTO params);
	
	public BoardDTO detailBoard(Long idx);
	
	public int updateBoard(BoardDTO params);
	
	public int deleteBoard(Long idx);
	
	public List<BoardDTO> listBoard();
	
	public int totalBoardCount();
	
	public int plusView(Long idx);
	
	
	
	

}
