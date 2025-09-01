package com.kd.springdatabase.board.service;

import org.springframework.stereotype.Service;

import com.kd.springdatabase.board.dto.BoardDto;
import com.kd.springdatabase.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	
	@Override
	public BoardDto detail(Integer board_id) {
		boardMapper.plusViewCount(board_id);
		
		BoardDto boardDto = boardMapper.get(board_id);
		
		return boardDto;
	}

	
}
