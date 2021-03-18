package com.myboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		
		boolean result = boardService.resigsterBoard(params);
		
		
	}
}
