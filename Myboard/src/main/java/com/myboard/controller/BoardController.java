package com.myboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myboard.domain.BoardDTO;
import com.myboard.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value ="/myboard/write.do")
	public String openWriter(@RequestParam(value ="idx" , required = false) Long idx,Model model) {
		
		if (idx == null) {
			model.addAttribute("board",new BoardDTO());
		} else {
			BoardDTO board = boardService.detailBoard(idx);
			
			if (board == null) {
				return "redircet:/board/list.do";
			}
			model.addAttribute("board",board);
		}
		return "board/write";
	}
	
	@PostMapping(value = "/myboard/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegisterd = boardService.resigsterBoard(params);
				
			if (isRegisterd == false) {
				//게시글 등록 실패
			}
			
		} catch (DataAccessException e) {
			//데이터베이스 치러 실패
		} catch (Exception e) {
			//시스템 문제
	}
		return "redirect:/board/list.do";

	}
	
	@GetMapping(value = "/myboard/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.ListBoard();
		model.addAttribute("boardList",boardList);
		
		return "board/list";
	}
}
