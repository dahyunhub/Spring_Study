package com.kd.springmvc.controller.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Chef {
	String name;
	Integer carrer;
	String field;
}
