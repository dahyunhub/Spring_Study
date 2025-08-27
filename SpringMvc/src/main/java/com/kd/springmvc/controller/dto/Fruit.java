package com.kd.springmvc.controller.dto;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Fruit {
	@NonNull
	private String name;
	private Integer price;
	private String farm;
}
