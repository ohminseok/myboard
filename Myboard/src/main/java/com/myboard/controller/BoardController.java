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
	
	@GetMapping(value = "/myboard/write.do")
	public String openWrtie(@RequestParam(value = "idx", required = false) Long idx , Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.Detail(idx);
			
			if (board == null) {
				return "redirect:/myboard/index.do";
			}
			
			model.addAttribute("board",board);
		}
		
		return "myboard/write";
	}
	
	@PostMapping(value = "/myboard/register.do")
	public String registerBoard(BoardDTO params) {
		boolean isRegisterd = boardService.register(params);
		
		try {
			if (isRegisterd == false) {
				return "redirect:/myboard/index.do";
			}
			
		} catch (DataAccessException e) {
			
		} catch (Exception e) {
			
		}
		
		return "redirect:/myboard/index.do";
	}
	
	@GetMapping(value = "/myboard/index.do")
	public String ListBoard(Model model) {
		List<BoardDTO> boardList = boardService.List();
		
		model.addAttribute("boardList", boardList);
		
		return "myboard/index";
	}
	
	@GetMapping(value = "/myboard/view.do")
	public String DetailBoard(@RequestParam(value = "idx" , required = false) Long idx,Model model) {
		
		if (idx == null) {
			return "redirect:/myboard/index.do";
		}
		
		BoardDTO board = boardService.Detail(idx);
		if (board == null && "Y".equals(board.getDeleteYn())) {
			
			return "redirect:/myboard/index.do";
		}
		
		model.addAttribute("board",board);
		boardService.Count(idx);
		
		
		return "myboard/view";
	}
	
	@PostMapping(value = "/myboard/delete.do")
	public String DeleteBoard(@RequestParam(value = "idx" ,required = false) Long idx) {
		boolean isDeleted = boardService.Delete(idx);
			
			try {
				if (isDeleted == false) {
					return "redirect:/myboard/index.do";
				}
				
			} catch (DataAccessException e) {
				
			} catch (Exception e) {
				
			}
			
			return "redirect:/myboard/index.do";
		}
}