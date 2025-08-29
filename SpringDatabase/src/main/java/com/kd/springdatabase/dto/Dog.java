package com.kd.springdatabase.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Dog {
	private String name;
	private Integer age;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regist_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date regist_date2;
}
