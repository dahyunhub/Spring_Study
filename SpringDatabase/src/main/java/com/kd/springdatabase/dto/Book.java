package com.kd.springdatabase.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Book {
	private Integer book_id;
	private String book_title;
	private String writer;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publication_date;
}
