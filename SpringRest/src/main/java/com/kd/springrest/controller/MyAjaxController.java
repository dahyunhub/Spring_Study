package com.kd.springrest.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd.springrest.dto.AnimalDto;
import com.kd.springrest.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/ajax")
@RestController
public class MyAjaxController {
	
	// Controller에 이런 것을 만드는 것은 굉장히 위험하다.
	// 웹 서버는 여러 사람이 한꺼번에 요청을 하는 곳이기 때문이다..
	// 웹 서버에서 컨트롤러 인스턴스(뿐만 아니라 스프링 컨텍스트에 등록되는 모든 것들)는 
	// 싱글톤으로 관리하기 때문에 이곳에서 인스턴스 필드를 만들어도 스태틱이나 다름없다.
	private final String[] messages = {
		"이 메세지는 스프링 컨트롤러에서 최초로 시작되어...",
		"일년에 열 바퀴 돌면서 받는 사람에 행운을 가져다 주었고...",
		"지금 당신에게로 옮겨진 이 편지는 3일 안에 다른 사람에게 전달해주어야 합니다..."
	};

	private final int[] statusArr = {200, 404, 500};
	
	// 이런 인덱스를 각자의 세션 또는 쿠키에 넣어줘야 잘 돌아간다..
	int index = 0;
	
	@GetMapping(value = "/value1", produces = MediaType.TEXT_PLAIN_VALUE)
	public String value1(HttpServletRequest request) {
		log.info("Ajax 요청이 잘 도착함!({})", request.getRemoteAddr());
		
		return messages[index++ % messages.length];
	}
	
	@GetMapping(
			value = "/value2", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public AnimalDto value2() {
		return new AnimalDto("돌고래", 5, "포유류");
	}
	
	@GetMapping("/req1")
	public ResponseEntity<String> req1(
			@RequestParam Integer board_id,
			@RequestParam String query) {
		
		log.info("board_id? {}", board_id);
		log.info("query? {}", query);
		
		return ResponseEntity.ok("ok! thank you!");
	}
	
	@PostMapping("/req2")
	public String req2(
			@RequestParam Integer board_id,
			@RequestParam Integer employee_id) {
		log.info("board_id: {}", board_id);
		log.info("employee_id: {}", employee_id);
		return "data";
	}
	
	@PostMapping("/req3")
	public String req3(@RequestBody AnimalDto animal) {
		log.info("전달 받은 동물: {}", animal);
		return "sugo";
	}
	
	
	@PutMapping("/board/{id}")
	public String req4(
			@PathVariable("id") Integer board_id,
			@RequestBody
			BoardDto boardDto) {
		log.info("{}번 게시글을 수정합니다.", board_id);
		log.info("수정할 내용: {}", boardDto);
		
		return "oo";
	}
	
	@DeleteMapping("/board/{board_id}")
	public String deleteBoard(
			@PathVariable() Integer board_id) {
		log.info("{}번 글을 삭제해야합니다.", board_id);
		
		return "삭제가 잘 됐습니다.";
	}
	
	@PatchMapping("/status")
	public ResponseEntity<String> randomStatus() {
		int status = statusArr[(int)(Math.random() * statusArr.length)];
		
		return ResponseEntity.status(status).body(""+status);
	}
}
