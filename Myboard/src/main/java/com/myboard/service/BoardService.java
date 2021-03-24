package com.myboard.service;

import java.util.List;

import com.myboard.domain.BoardDTO;
import com.myboard.paging.Criteria;

public interface BoardService {
	
	public boolean register(BoardDTO params);
	
	public BoardDTO Detail(Long idx);
	
	public boolean delete(Long idx);
	
	public List<BoardDTO> List(BoardDTO params);
	
	public int ViewPlus(Long idx);

}
