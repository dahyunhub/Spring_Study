package com.kd.springdatabase.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kd.springdatabase.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/detail/{board_id}")
	public String detail(
			@PathVariable Integer board_id, Model model) {
		log.info("board_id from path: {}", board_id);
	
		model.addAttribute("boardDto", boardService.detail(board_id));
		
		return "board/detail";
	}
}
