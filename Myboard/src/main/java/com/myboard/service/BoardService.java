package com.myboard.service;

import java.util.List;

import com.myboard.domain.BoardDTO;
import com.myboard.paging.Criteria;

public interface BoardService {
	
	public boolean registerBoard(BoardDTO params);
	
	public BoardDTO getDetailBoard(Long idx);
	
	public boolean deleteBoard(Long idx);
	
	public List<BoardDTO> getListBoard();
	
	public int getViewPlus(Long idx);

}
