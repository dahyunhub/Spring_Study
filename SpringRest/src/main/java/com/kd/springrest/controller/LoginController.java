package com.kd.springrest.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
	
@Slf4j
@RequestMapping("/login")
@Controller
public class LoginController {

	@GetMapping("/form")
	public String login() {
		return "/login/loginForm";
	}
	
	@PostMapping("/submit")
	@ResponseBody
	public Map<String, Object> info(String user_id, String user_password, HttpSession session, Model model) {
		Map<String, Object> result = new HashMap<>();
		
		if (user_id == null || user_password == null) {
			result.put("success", false);
			result.put("message", "로그인실패");
		
			
		} else if ("1234".equals(user_id) && "5678".equals(user_password)) {
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_password", user_password);
			
			
			
			result.put("success", true);
			result.put("message", "로그인성공");
			
			log.info("id:" + user_id + " pw:" + user_password);
			
		} else {
			result.put("success", false);
			result.put("message", "로그인실패");
		}
		return result;
	}
	
	@GetMapping("/logined")
	public String logined() {
		return "login/logined";
	}
}
