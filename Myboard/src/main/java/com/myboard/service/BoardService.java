package com.myboard.service;

import java.util.List;

import com.myboard.domain.BoardDTO;

public interface BoardService {
	
	public boolean resigsterBoard(BoardDTO params);
	
	public BoardDTO detailBoard(Long idx);
	
	public boolean dropBoard(Long idx);
	
	public List<BoardDTO> ListBoard();
	
	public Long viewPlus(Long idx);

}
