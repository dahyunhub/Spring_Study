package com.kd.springdatabase.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kd.springdatabase.dto.Employee;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // DB의 수거대상이 된다.
public class EmployeeMapper {
	
	@Autowired
	private final SqlSessionTemplate sql;
	
	public List<Employee> selectAll() {
		return sql.selectList("Employees.selectAll");
	}
	
	public Employee selectOne(Integer employee_id) {
		return sql.selectOne("Employees.selectOne", employee_id);
	}
	
}
