package com.myboard.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myboard.domain.BoardDTO;
import com.myboard.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean resigsterBoard(BoardDTO params) {
		int quaryResult = 0;
		
		if(params.getIdx() == null) {
			quaryResult = boardMapper.insertBoard(params);
			
		} else {
			quaryResult = boardMapper.updateBoard(params);
		}
		
		return (quaryResult == 0) ? false : true;
	}

	@Override
	public BoardDTO detailBoard(Long idx) {
		// TODO Auto-generated method stub
		return boardMapper.selectDetail(idx);
	}

	@Override
	public boolean dropBoard(Long idx) {
		int quaryResult = 0;
		
		BoardDTO board = boardMapper.selectDetail(idx);
		
		if (board != null && "N".equals(board.getDeleteYn())) {
			quaryResult = boardMapper.deleteBoard(idx);
		}
		
		return (quaryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> ListBoard() {
		// TODO Auto-generated method stub
		List<BoardDTO> boardList = Collections.emptyList();
		
		int boardTotalCount = boardMapper.totalCount();
		
		if (boardTotalCount > 0) {
			boardList = boardMapper.selectList();
		}

		return boardList;
	}
	
	@Override
	public Long viewPlus(Long idx) {
		return boardMapper.viewPlus(idx);
	}

}
