package com.kd.springmvc.controller.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Hotel {
	private String name;
	private String address;
	private final Chef chef; 
	// final을 붙이면 생성자가 붙어서 알아서 객체를 입력해준다.
	// final을 안 붙이면 chef가 null로 나온다.
}
