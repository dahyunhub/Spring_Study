package com.kd.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/fruit")
@Controller
public class TestController3 {

	@GetMapping("/apple")
	public String apple() {
		return "/fruit/apple"; // 실제로는 templates 밑의 /fruit/apple.html을 찾아감
	}
	
	@GetMapping("/banana")
	public void banana(Model model) {
		// 이곳에 접속할 때 사용한 경로가 /fruit/banana이므로 
		// templates 밑의 /fruit/banana.html을 찾아감
		
		model.addAttribute("price", 5500);
	}
	
	@GetMapping("/grape")
	public ModelAndView grape() {
		ModelAndView mnv = new ModelAndView();
		
		// 실어놓고 싶은 정보를 실어놓기
		mnv.addObject("qty", 35);
		
		// 다음으로 갈 페이지를 정하기
		mnv.setViewName("/fruit/grape");
		return mnv;
	}
	
	@GetMapping("/random")
	@ResponseBody
	public String randomFruit() {
		String[] fruits = {"사과", "바나나", "오렌지", "키위"};

		// @ResponseBody가 달려있으므로 
		// 템플릿 밑에서 해당 과일 경로를 찾는 것이 아니라 그냥 문자열을 반환한
		return fruits[(int)(Math.random() * fruits.length)];
	}
}
