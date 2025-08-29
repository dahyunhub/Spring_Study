package com.kd.springdatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Fruit {
	private Integer fruit_id;
	private String fruit_name;
	private Integer fruit_price;
	private String farm;
}
