package com.myboard;


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
	void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setIdx((long)1);
		params.setTitle("테스트");
		params.setContent("테스트");
		params.setWriter("테스트");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("result = " + result);
	}
	
	@Test
	void testOfDetail() {
		BoardDTO board = boardMapper.detailBoard((long)1);
		
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			System.out.println("==================");
			System.out.println(boardJson);
			System.out.println("==================");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testOfUpdate() {
		
		
		BoardDTO params = new BoardDTO();
		
		params.setTitle("수정 테스트");
		params.setContent("수정 테스트");
		params.setWriter("수정 테스트");
		params.setIdx((long)1);
		
		int result = boardMapper.updateBoard(params);
		
		if (result == 1) {
			BoardDTO board = boardMapper.detailBoard((long)1);
			
			try {
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
	void tesfOfDelete() {
		int result = boardMapper.deleteBoard((long)1);
		
		if (result == 1) {
			BoardDTO board = boardMapper.detailBoard((long)1);
			
			try {
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
	void tesfOfMultiple() {
		BoardDTO params = new BoardDTO();
		
		for (int i=1;i<100;i++) {
			params.setTitle(i + "번 째 아이디");
			params.setContent(i + "번 째 내용");
			params.setWriter(i + "번 째 이름");
			boardMapper.insertBoard(params);
		}
	}
	
	@Test
	public void testOfList() {
		int boardTotalCount = boardMapper.totalListCount();
		if (boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.listBoard();
			if (CollectionUtils.isEmpty(boardList) == false) {
				for (BoardDTO board : boardList) {
					System.out.println("=========================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("=========================");
				}
			}
		}
	}
}
