package com.myboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myboard.domain.BoardDTO;
import com.myboard.paging.Criteria;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardDTO params);
	
	public BoardDTO detailBoard(Long idx);
	
	public int updateBoard(BoardDTO params);
	
	public int deleteBoard(Long idx);
	
	public List<BoardDTO> listBoard(BoardDTO params);
	
	public int totalBoardCount(BoardDTO params);
	
	public int plusView(Long idx);
	
	
	
	

}
