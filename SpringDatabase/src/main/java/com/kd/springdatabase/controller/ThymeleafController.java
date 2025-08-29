package com.kd.springdatabase.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kd.springdatabase.dto.Fruit;

@RequestMapping("/thyme")
@Controller
public class ThymeleafController {

	@GetMapping(value ={"/", "/index", "/home"})
	public String index(Model model) {
		model.addAttribute("fruit", "수박");
		model.addAttribute("fruit", new Fruit(
				12345, "strawberry", 2000, null
				));
		
		Set<String> snacks = new HashSet<>();
		
		snacks.add("초코파이");
		snacks.add("딸기파이");
		snacks.add("바나나파이");
		snacks.add("멜론파이");
		
		model.addAttribute("snacks", snacks);
		
		return "/thyme/index";
	}
	
	@GetMapping("/frag")
	public String frag() {
		return "/thyme/frag/main";
	}
	
	@GetMapping("/form") 
	public String form(Model model) {
		model.addAttribute("f3", new Fruit(
				41, "banana", 2100, "봉이농장"));
		
		return "/thyme/form";
	}
	
}
