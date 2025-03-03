package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService;

@WebMvcTest(controllers = EmpController2.class)		//for testing rest apis
public class EmpController2Test {
	
	@MockitoBean
	private EmployeeService empService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testGetEmployees() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(MockMvcResultMatchers.status().is(200));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetEmployeeBelowAge() {
		try {
			List<Employee> list = new ArrayList<Employee>();
			list.add(new Employee());
			Mockito.when(empService.getEmployeeBelow(25)).thenReturn(list);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
