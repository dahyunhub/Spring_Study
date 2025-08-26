package com.kd.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// 이 컨트롤러에 도착하는 uri도 설정해 놓을 수 있다. 
@RequestMapping(value = {"/test", "/babo"})
@Controller
public class TestController1 {
	
	@RequestMapping("/t1")
	public String test1() {
		// 리턴하는 문자열은 templates 내부의 뷰 파일(.html) 경로를 의미한다. (Thymeleaf 기준)
		return "/test/test1";
	}
	
	// 여러개의 주소에 매핑하기
	@RequestMapping(value = {"/t2", "/t3"}) // 여러개 매핑하는 것도 가능!!
	public String test2() {
		return "/test/test1";
	}
	
	@RequestMapping(
			value = {"/t4", "/t5"}, 
			method = {RequestMethod.GET, RequestMethod.POST}) // 메소드 여러개 가능!!
	public String test3() {
		return "/test/test1";
	}
}
