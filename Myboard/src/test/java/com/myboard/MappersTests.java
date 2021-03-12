package com.myboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myboard.domain.BoardDTO;
import com.myboard.mapper.BoardMapper;

@SpringBootTest
public class MappersTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	void tesfOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("테스트");
		params.setContent("테스트");
		params.setWriter("테스트");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("result = " + result);
	}

}
