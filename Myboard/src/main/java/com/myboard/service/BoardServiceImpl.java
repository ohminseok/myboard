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
		
		int result = 0;
		
		if (params.getIdx() == null) {
			result = boardMapper.insertBoard(params);
		} else {
			result = boardMapper.updateBoard(params);
		}
		
		return (result == 1) ? true : false;
	}
	
	@Override
	public BoardDTO getDetailBoard(Long idx) {
		return boardMapper.detailBoard(idx);
	}
	
	@Override
	public boolean deleteBoard(Long idx) {
		int result = 0;
		
		BoardDTO board = boardMapper.detailBoard(idx);
		if (board != null && "N".equals(board.getDeleteYn()) ) {
			result = boardMapper.deleteBoard(idx);
		}
		
		return (result == 1) ? true : false;
	}
	
	@Override
	public List<BoardDTO> getListBoard() {
		List<BoardDTO>boardList = Collections.emptyList();
		
		int totalCount = boardMapper.totalListCount();
		
		if (totalCount > 0) {
			boardList = boardMapper.listBoard();
		}
		
		return boardList;
	}
	
	@Override
	public int getViewPlus(Long idx) {
		return boardMapper.viewPlusBoard(idx);
	}
	
	
}
	
	

