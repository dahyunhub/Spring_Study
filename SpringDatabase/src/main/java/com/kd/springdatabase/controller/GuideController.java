package com.kd.springdatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kd.springdatabase.dto.Fruit;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/guide")
@Controller
public class GuideController {


	@GetMapping("/")
	public void guide(HttpServletRequest request, Model model) {
		// 리다이렉트시에 
		// RedirectAttributes의 addAttribute는 파라미터로 도착하게 된다.
		log.info("abc: {}", request.getParameter("abc"));
		
		// flashAttribute로 실어보낸 어트리뷰트들은 받는 쪽의 Model 객체에 이미 추가되어 있다.
		log.info("fruit from flashAttribute: {}", model.getAttribute("fruit"));
		log.info("babo from flashAttribute: {}", model.getAttribute("babo"));
	}
	
	@GetMapping("/desc1")
	public String desc1() {
		return "desc1";
	}
	
	@GetMapping("/list")
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
	
	@GetMapping("/session1")
	public String sessionPage(HttpSession session) {
		session.setAttribute("icecream", "메로나");
		
		return "/guide/session";
	}
	
	@GetMapping("/session2")
	public String sessionPage2(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("icecream", "스크류바");
		
		return "/guide/session";
	}
	
	@GetMapping("/session3")
	public String sessionPage3(
		@SessionAttribute String icecream,
		@SessionAttribute(required = false) String flavor) {
//		required를 false로 설정한 어트리뷰트는 자동 주입을 받지 못하더라도
//		예외가 발생하지 않고 프로그램을 계속 진행한다.
//		세션 값이 없는 경우(required = false)를 입력해 null값이 나오도록 할 수 있다. (에러가 안뜸)
		
		log.info("icecream: {}", icecream);
		log.info("flavor: {}", flavor);
		return "/guide/session";
	}
	
	@GetMapping("/cookie/add") 
	public String cookidAdd(
			HttpServletRequest request,
			HttpServletResponse response
			) {
		Cookie cookie = new Cookie("email", "abc123@gmail.com");
		cookie.setPath(request.getContextPath());
		cookie.setHttpOnly(true); // 자바스크립트에서 못읽는 쿠키가 된다.
		cookie.setMaxAge(-1); // 웹 브라우저가 닫힐 때까지 유지
		
		response.addCookie(cookie);
		
		// contextPath가 있는 경우 알아서 더해주도록 구현되어 있다. 
		return "redirect:/guide/"; 
		
	}
	
	@GetMapping("/cookie/read")
	public String cookieRead(
			@CookieValue String email, Model model) {
		log.info("email from cookie: {}", email);
		
		// addAttribute할 때 name을 설정하지 않으면..?
		// -> 해당 클래스 타입의 첫 글자를 소문자로 바꾼 것이 어트리뷰트 이름이 된다. 
		//	  (email은 String 클래스이므로 string이 됨)
		model.addAttribute(email);
		
		return "/guide/cookie";
	}
}
