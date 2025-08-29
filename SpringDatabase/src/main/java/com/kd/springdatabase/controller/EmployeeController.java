package com.kd.springdatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kd.springdatabase.dto.Employee;
import com.kd.springdatabase.mapper.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/emp")
@Controller
public class EmployeeController {

	@Autowired
	EmployeeMapper employeeMapper; 
	// 아까 EmployeeMapper에서 @Repository라고 해둬서 위처럼 사용할 수 있다. 
	
	@GetMapping("/list")
	public void list(Model model) {
		List<Employee> employees = employeeMapper.selectAll();
		
		for (Employee employee : employees) {
//			log.info("{}", employee);
			
		}
		
		model.addAttribute("employees", employees);
	}
}
