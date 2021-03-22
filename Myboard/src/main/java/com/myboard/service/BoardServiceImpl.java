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
		
		int quaryResult = 0;
		
		if (params.getIdx() == null) {
			quaryResult = boardMapper.insertBoard(params);
		} else {
			quaryResult = boardMapper.updateBoard(params);
		}
		
		return (quaryResult == 1) ? true : false;
	}
	
	@Override
	public BoardDTO Detail(Long idx) {
		return boardMapper.detailBoard(idx);
	}
	
	@Override
	public boolean delete(Long idx) {
		
		int quaryResult = 0;
		
		BoardDTO board = boardMapper.detailBoard(idx);
		
		if (board != null && "N".equals(board.getDeleteYn())) {
			quaryResult = boardMapper.deleteBoard(idx);
		}
		
		return (quaryResult == 1) ? true : false;
	}
	
	@Override
	public List<BoardDTO> List() {
		List<BoardDTO> boardList = Collections.emptyList();
		
		int TotalCount = boardMapper.totalBoardCount();
		
		if( TotalCount > 0 ) {
			boardList = boardMapper.listBoard();
		}
		
		
		return boardList;
	}
	
	@Override
	public int ViewPlus(Long idx) {
		return boardMapper.plusView(idx);
	}
}
	
	

