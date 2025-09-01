package com.kd.springrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@GetMapping(value = {"/", "/home", "/index"})
	public String index() {
		return "index";
	}
	
	// 일반 컨트롤러에서도 @ResponseBody를 사용하면 Restful API를 작성할 수 있다.
	@ResponseBody
	@GetMapping(value = "/value1", produces = "text/html; charset=UTF-8")
	public String value1() {
		return "<h1>Hello</h1>";
				
	}
}
