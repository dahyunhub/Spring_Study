package com.kd.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kd.springmvc.controller.dto.Coffee;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class TestController2 {

    private final HomeController homeController;

    TestController2(HomeController homeController) {
        this.homeController = homeController;
    }
	
	@GetMapping("/coffee/add")
	public String coffeeAdd() {
		return "/coffee/coffeeAddForm";
	}
	
	@PostMapping("/coffee/add")
	public String add(HttpServletRequest request) {
		String coffee_name = request.getParameter("coffee_name");
		String coffee_bean = request.getParameter("coffee_bean");
		String coffee_price = request.getParameter("coffee_price");
		
		System.out.println("coffee_name: " + coffee_name);
		System.out.println("coffee_bean: " + coffee_bean);
		System.out.println("coffee_price: " + coffee_price);
		
		return "redirect:/coffee/add";
	}
	
	// 해당 파라미터를 필드로 갖는 모델 객체를 매개변수로 활용하면
	// 파라미터에 있는 값을 활용하여 자동으로 만들어진 인스턴스가 전달된다.
	// (파라미터 값의 타입도 알아서 변환해준다. = 이런걸 바인딩이라고 함)
	
	@PostMapping("/coffee/add2")
	public String add2(Coffee coffee, Model model) {
		System.out.println("coffee to add: " + coffee);
		
		//DB에 추가를 잘하고 ID를 얻어왔다고 가정....
		coffee.setCoffee_id(138);
		
		// 값을 다음 페이지로 실어보낼 때는 어트리뷰트가 아니라 모델 객체를 사용하는 것이 바람직하다.
		model.addAttribute("coffee", coffee);
		model.addAttribute("result", "failed");
		
		
//		request.setAttribute("coffee", coffee);
//		request.setAttribute("result", "success");
		
		return "/coffee/add_result";
	}
	
	// 컨트롤러에 전달되는 파라미터의 이름과 똑같은 이름의 매개 변수를 선언해두면
	// 파라미터 값을 알아서 바인딩해준다.
	// 이건 dto가 없어도 가능하다. 파라미터가 알아서 들어온다.
	
	// RequestParam 어노테이션을 활용해 매개 변수에 파라미터 값을 바인딩하는 방법도 있다.
	
	@PostMapping("/coffee/add3")
	public String add3(
			@RequestParam("coffee_name") String coffee_name, 
			@RequestParam("coffee_bean") String coffee_bean, 
			@RequestParam("coffee_price") String coffee_price) {
		
		System.out.println("coffee_name: " + coffee_name);
		System.out.println("coffee_bean: " + coffee_bean);
		System.out.println("coffee_price: " + coffee_price);
		
		return "redirect:/coffee/add";
	}
	
//	🔍 왜 이런 일이 생기냐?
//			자바는 기본적으로 컴파일 시 메서드 파라미터 이름을 class 파일에 저장하지 않아.
//			그러니까 Spring이 그걸 reflection으로 읽으려 해도 못 읽는 거야.
//
//			예:
//			public String add3(String coffee_name) 
//
//			이 코드를 컴파일하면 coffee_name이라는 이름은 class 파일 안에 없고, 그냥 arg0 같은 식으로 저장돼.
//			→ 그래서 Spring은 coffee_name이 뭔지 몰라서 오류가 나는 거야.
	
//	@PostMapping("/coffee/add3")
//	public String add3(
//			String coffee_name, 
//			String coffee_bean, 
//			Integer coffee_price) {
//		
//		System.out.println("coffee_name: " + coffee_name);
//		System.out.println("coffee_bean: " + coffee_bean);
//		System.out.println("coffee_price: " + coffee_price);
//		
//		return "redirect:/coffee/add";
//	}
	
}
