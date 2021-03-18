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
				return "redirect:/myboard/index.do";
			}
			model.addAttribute("board",board);
		}
		return "myboard/write";
	}
	
	@PostMapping(value = "/myboard/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegisterd = boardService.resigsterBoard(params);
				
			if (isRegisterd == false) {
				//게시글 등록 실패
				System.out.println("실패");
			}
			
		} catch (DataAccessException e) {
			//데이터베이스 치러 실패
			System.out.println("실패1");
		} catch (Exception e) {
			//시스템 문제
			System.out.println("실패2");
	}
		return "redirect:/myboard/index.do";

	}
	
	@GetMapping(value = "/myboard/index.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.ListBoard();
		model.addAttribute("boardList",boardList);
		
		return "myboard/index";
	}
	
	@GetMapping(value = "/myboard/view.do")
	public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx,Model model) {
		if (idx == null) {
			//아예 번호가 없을때
			return "redirect:/myboard/index.do";
		}
		BoardDTO board = boardService.detailBoard(idx);
		if (board == null && "Y".equals(board.getDeleteYn())) {
			//내용이 비었거나 삭제된 글 일떄
			return "/myboard/index.do";
		}
		model.addAttribute("board",board);
		boardService.viewPlus(idx);
		
		return "myboard/view";
	}
	
	@PostMapping(value = "/myboard/delete.do")
	public String deleteBoard(@RequestParam(value = "idx" , required = false) Long idx) {
		if (idx == null) {
			System.out.println("실패1");
			return "redirect:/myboard/list.do";
		}
		
		try {
		
			boolean isDeleted = boardService.dropBoard(idx);
		
			if (isDeleted == false) {
				//실패 메세지
				System.out.println("실패2");
			}
		
		}catch(DataAccessException e) {
			//데이터 베이스 처리과정 문제
			System.out.println("실패3");
		}catch(Exception e) {
			System.out.println("실패4");
		}
		
		return "redirect:/myboard/list.do";
	}
}
