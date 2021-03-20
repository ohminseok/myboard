package com.myboard.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.myboard.domain.BoardDTO;
import com.myboard.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean register(BoardDTO params) {
		
		int queryResult = 0;
		
		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public BoardDTO Detail(Long idx) {
		return boardMapper.detailBoard(idx);
	}
		
	@Override
	public boolean Delete(Long idx) {
		
		int queryResult = 0;
		
		BoardDTO board = boardMapper.detailBoard(idx);
		
		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}
			
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<BoardDTO> List() {
		List<BoardDTO> boardList = Collections.emptyList();
		
		int boardTotalCount = boardMapper.totalBoardCount();
		
		if (boardTotalCount > 0) {
			
			boardList = boardMapper.listBoard();
		}
		
		return boardList;
	}
	
	@Override
	public int Count(Long idx) {
		return boardMapper.plusView(idx);
	}
		
}
	
	

