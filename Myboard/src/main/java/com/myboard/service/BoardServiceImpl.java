package com.myboard.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.myboard.domain.BoardDTO;
import com.myboard.mapper.BoardMapper;
import com.myboard.paging.Criteria;
import com.myboard.paging.PaginationInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		} else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public BoardDTO getDetailBoard(Long idx) {
		return boardMapper.detailBoard(idx);
	}
	
	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;
		
		BoardDTO board = boardMapper.detailBoard(idx);
		
		if (board != null && "N".equals(board.getDeleteYn())) {
			
			queryResult = boardMapper.deleteBoard(idx);
		}
		
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<BoardDTO> getListBoard() {
		List<BoardDTO> boardList = Collections.emptyList();
		
		int totalBoardCount = boardMapper.totalListCount();
		
		if (totalBoardCount > 0) {
			
			boardList = boardMapper.listBoard();
		}
		
		return boardList;
	}
}
	
	

