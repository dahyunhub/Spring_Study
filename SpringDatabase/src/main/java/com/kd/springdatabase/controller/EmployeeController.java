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
		
		// 1. 하고싶은 처리를 한다. (Service) // 1번 절차의 소스코드가 많아지고, 재사용 빈도가 높아지므로 주로 service 객체를 따로 만든다.
		//   (1) DB에 접근해야 한다면 DB에서 데이터를 꺼내온다.
		//	 (2) 꺼내온 데이터에 대한 처리를 진행해야 한다.
		
		// 2. 처리로 인해 생긴 데이터들을 모두 모델에 실어놓는다.
		model.addAttribute("employees", employees);
		
		// 3. 다음으로 갈 뷰(템플릿 or jsp)를 찾아간다. (Controller의 메인 역할)
		
//		for (Employee employee : employees) {
//			log.info("{}", employee);
//		}
		
	}
}
