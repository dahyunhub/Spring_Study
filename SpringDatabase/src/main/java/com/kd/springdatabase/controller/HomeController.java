package com.kd.springdatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kd.springdatabase.dto.Fruit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping(value= {"/index", "/home", "/"})
	public String index() {
		return "/index";
	}
	

	@GetMapping("/guide")
	public void guide(HttpServletRequest request, Model model) {
		// 리다이렉트시에 
		// RedirectAttributes의 addAttribute는 파라미터로 도착하게 된다.
		log.info("abc: {}", request.getParameter("abc"));
		
		// flashAttribute로 실어보낸 어트리뷰트들은 받는 쪽의 Model 객체에 이미 추가되어 있다.
		log.info("fruit from flashAttribute: {}", model.getAttribute("fruit"));
		log.info("babo from flashAttribute: {}", model.getAttribute("babo"));
	}
	
	@GetMapping("/guide/desc1")
	public String desc1() {
		return "desc1";
	}
	
	@GetMapping("/guide/list")
	public String to_list(RedirectAttributes rattr) {
		// GET방식 쿼리 스트링(파라미터) 추가
		rattr.addAttribute("abc", 123);
		
		// 리다이렉트시 addAttribute는 사실 쿼리 스트링이기 때문에
		// 자바 객체는 불가능하고 String만 가능하다.
		//rattr.addAttribute("fruit", new Fruit(1, "감자", 500, "농장"));

		// 실제로는 세션 객체를 활용하여 동작하는 다음 리다이렉트까지만 유지되는 어트리뷰트
		// 필요한 경우 자바 객체를 전달할 수 있다.
		rattr.addFlashAttribute("fruit", new Fruit(3, "토마토", 3333, "도마도농장"));
		rattr.addFlashAttribute("babo", "진짜바보상처받는다");
		
		return "redirect:/guide";
	}
	
	
	
}
