package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.repos.EmpDao;
import com.example.demo.service.EmployeeService;

@RestController
public class EmpController2 {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employees")
	public Iterable<Employee> getEmployees(){
		return service.getEmployees();
	}
	
	@PostMapping("/employees")
	public String addEmployees(@RequestBody Employee e){
		return service.addEmployees(e);
	}
	
//	@GetMapping("/employees")
//	public Optional<Employee> getEmployeeByID(int id){
//		return service.getEmployeeByID(id);
//	}
	
	@RequestMapping(path="/employees/{id}", method = {RequestMethod.PUT,RequestMethod.PATCH})
	public String updateEmployees(@RequestBody Employee e, @PathVariable int id){
		return service.updateEmployees(e, id);
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployees(@PathVariable int id){
		return service.deleteEmployees(id);
	}
	
	@GetMapping("/employees/role")
	public List<Employee> getEmployeeByDesig(String desig){
		return service.getEmployeeByDesig(desig);
	}
	
	@GetMapping("/employees/above")
	public List<Employee> getEmployeeAbove(int age){
		return service.getEmployeeAbove(age);
	}
	
	@GetMapping("/employees/below")
	public List<Employee> getEmployeeBelow(int age){
		return service.getEmployeeBelow(age);
	}
	
	@GetMapping("/employees/custom")
	public List<Employee> getCustomQuery(@RequestParam String desig){
		return service.getCustomQuery(desig);
	}
	
}
