package com.myboard;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myboard.domain.BoardDTO;
import com.myboard.domain.MemberDTO;
import com.myboard.mapper.BoardMapper;
import com.myboard.mapper.MemberMapper;

@SpringBootTest
public class MappersTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("테스트");
		params.setContent("테스트");
		params.setWriter("테스트");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("result = " + result);
	}
	
	@Test
	public void testOfDetail() {
		BoardDTO board = boardMapper.detailBoard((long)32);
		
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			
			System.out.println("=============================");
			System.out.println(boardJson);
			System.out.println("=============================");
		} catch (JsonProcessingException e) {
		
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		
		
		params.setTitle("1");
		params.setContent("1");
		params.setWriter("1");
		params.setIdx((long)32);
		
		int result = boardMapper.updateBoard(params);
		
		if (result == 1) {
			
			BoardDTO board = boardMapper.detailBoard((long)32);
			
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("======================");
				System.out.println(boardJson);
				System.out.println("======================");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("망");
		}
		
		
	}
	
	@Test
	public void testOfDelete() {
		
		int result = boardMapper.deleteBoard((long)32);
		
		if (result == 1) {
			try {
				BoardDTO board = boardMapper.detailBoard((long)32);
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("==================");
				System.out.println(boardJson);
				System.out.println("==================");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfList() {
		int boardTotalCount = boardMapper.totalListCount();
		
		if (boardTotalCount > 0) {
			
			List<BoardDTO> boardList = boardMapper.listBoard();
			
			if (CollectionUtils.isEmpty(boardList)) {
				for(BoardDTO board : boardList) {
					System.out.println("============");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("============");
				}
			}
		}
	}

}
