package com.myboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myboard.constant.Method;
import com.myboard.domain.BoardDTO;
import com.myboard.mapper.BoardMapper;
import com.myboard.paging.Criteria;
import com.myboard.service.BoardService;
import com.myboard.util.UiUtils;

@Controller
public class BoardController extends UiUtils {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "/myboard/write.do")
	public String openWrite(@RequestParam(value = "idx" , required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getDetailBoard(idx);
			
			if (board == null) {
				return "redirect:/myboard/index.do";
			}
			model.addAttribute("board",board);
		}
		return "myboard/write";
	}
	
	@PostMapping(value = "/myboard/register.do")
	public String registerBoard(final BoardDTO params, Model model) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/myboard/index.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/myboard/index.do", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/myboard/index.do", Method.GET, null, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/myboard/index.do", Method.GET, null, model);
	}
	
	@GetMapping(value = "/myboard/index.do")
	public String openList(Model model) {
		List<BoardDTO> boardList = boardService.getListBoard();
		
		model.addAttribute("boardList",boardList);
		
		return "myboard/index";
	}
	
	@GetMapping(value = "/myboard/view.do")
	public String openViewDetail(@RequestParam(value="idx" , required = false) Long idx,Model model) {
		if (idx == null) {
			return "redirect:/myboard/index.do";
		} else {
			BoardDTO board = boardService.getDetailBoard(idx);
			if (board == null || "Y".equals(board.getDeleteYn())) {
				return "redirect:/myboard/index.do";
			}
			
			model.addAttribute("board",board);
		}
		return "/myboard/view";
	}
	
	@PostMapping(value = "/myboard/delete.do")
	public String deleteBoard(@RequestParam(value = "idx",required = false)Long idx,Model model) {
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/myboard/index.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/myboard/index.do", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/myboard/index.do", Method.GET, null, model);
		}

		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/myboard/index.do", Method.GET, null, model);
	}
	

		
}