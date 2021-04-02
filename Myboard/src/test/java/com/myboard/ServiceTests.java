package com.myboard;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.myboard.domain.BoardDTO;
import com.myboard.service.BoardService;

@SpringBootTest
public class ServiceTests {

	@Autowired
	private BoardService boardService;
	
	@Test
	void testOfInsert() {
		BoardDTO params = new BoardDTO();
		
		params.setTitle("테스트");
		params.setWriter("테스트");
		params.setContent("테스트");
		
		boolean result = boardService.registerBoard(params);
		
		System.out.println("result = " + result);
		
		
	}
	
	@Test
	void testOfList() {
		BoardDTO params = new BoardDTO();
		

			List<BoardDTO> boardList = boardService.listBoard();
			
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

