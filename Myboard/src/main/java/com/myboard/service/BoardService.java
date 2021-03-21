package com.myboard.service;

import java.util.List;

import com.myboard.domain.BoardDTO;

public interface BoardService {
	
	public boolean register(BoardDTO params);
	
	public BoardDTO Detail(Long idx);
	
	public boolean delete(Long idx);
	
	public List<BoardDTO> List();
	
	public int ViewPlus(Long idx);

}
