package com.kd.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kd.springmvc.controller.dto.Chef;
import com.kd.springmvc.controller.dto.Hotel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DiTestController {

//  기본 package 밖에 myutils package 안에 있는 MyAwesomeClass가 있다고 가정
//	@Autowired
//	MyAwesomeClass myAwesomeClass;
	
	@Autowired
	Chef chef;
	
	private Chef sub_chef;
	
	@Autowired
	public void setSub_chef(Chef sub_chef) {
		this.sub_chef = sub_chef;
	}
	
	// 해당 필드를 private final로 설정하고 
	private final Hotel hotel;
	
	// 생성자를 통해 초기화를 반드시 해야하는 상황을 만들어 두면 스프링이 자동으로 해당 의존성을 주입해준다.
	public DiTestController(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@GetMapping(value = {"/di/", "/di/index"})
	public String index() {
		return "/di/index";
	}
	
	@GetMapping("/di/chef")
	public String chef() {
//		System.out.println("chef: " + chef);
		log.info("chef: {}" + chef);
//		System.out.println("sub chef: " + sub_chef);
		log.info("sub chef: {}" + sub_chef);
	return "redirect:/di/";
	}
	
	@GetMapping("/di/hotel")
	public String hotel() {
		System.out.println("hotel: " + hotel);
		
		return "redirect:/di/";
	}
}
