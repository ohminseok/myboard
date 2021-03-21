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
	
	@Autowired 
	private MemberMapper memberMapper;
	
	@Test
	void memberOfInsert() {
		MemberDTO member = new MemberDTO();
		member.setIdentify("첫번쨰");
		member.setPassword("123456");
		member.setEmail("123@naver.com");
		
		int result = memberMapper.insertMember(member);
	}
	
	@Test
	void memberOfList() {
		
	}
	
	@Test
	void tesfOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("테스트");
		params.setContent("테스트");
		params.setWriter("테스트");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("result = " + result);
	}
	
	@Test
	void testOfDetail() {
		BoardDTO board = boardMapper.detailBoard((long)123);
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			System.out.println("===========================");
			System.out.println("boardJson = " + boardJson);
			System.out.println("===========================");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		
		params.setTitle("수정1");
		params.setContent("수정1");
		params.setWriter("수정1");
		params.setIdx((long)123);
		
		int result = boardMapper.updateBoard(params);
		
		BoardDTO board = boardMapper.detailBoard((long)123);
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			System.out.println("===========================");
			System.out.println("boardJson = " + boardJson);
			System.out.println("===========================");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testOfDelete() {
		
		int result = boardMapper.deleteBoard((long)123);
		
		if (result == 1) {
			BoardDTO board = boardMapper.detailBoard((long)123);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				System.out.println("===========================");
				System.out.println("boardJson = " + boardJson);
				System.out.println("===========================");
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("삭제가 안됬습니다");
		}
	}
	
	@Test
	void testOfMultiple() {
		BoardDTO params = new BoardDTO();
		for (int i=2; i<=49; i++) {
			params.setTitle(i+"번째 제목");
			params.setContent(i+"번째 내용");
			params.setWriter(i+"번째 글쓴이");
		
		int result = boardMapper.insertBoard(params);
		}
	}
	
	@Test
	void testOfList() {
		BoardDTO params = new BoardDTO();
		int boardTotalCount = boardMapper.totalBoardCount();
		
		if (boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.listBoard();
			
			if (CollectionUtils.isEmpty(boardList) == false) {
				for (BoardDTO board : boardList) {
					System.out.println("========================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("========================");
				}
			}
		}
	}
	

	

}
