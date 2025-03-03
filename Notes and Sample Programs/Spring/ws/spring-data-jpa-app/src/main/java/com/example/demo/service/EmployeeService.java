package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Employee;
import com.example.demo.repos.EmpDao;

public class EmployeeService {	
		
	@Autowired
	EmpDao dao;
	
	public Iterable<Employee> getEmployees(){
		return dao.findAll();
	}
	public Optional<Employee> getEmployeeByID(int id){
		return dao.findById(id);
	}
	public String addEmployees(Employee e){
		if(dao.existsById(e.getEid()))
			return "Sorry! ID already exists";
		dao.save(e);
		return "Employee saved successfully...";
	}
	
	public String updateEmployees(Employee e, int id){
		if(!dao.existsById(id))
			return "Sorry! No employee with given ID exists to update";
		dao.save(e);
		return "Updated employee record successfully...";
	}
	
	public String deleteEmployees(int id){
		if(!dao.existsById(id))
			return "Sorry! Employee doesn't exist";
		dao.deleteById(id);
		return "Employee deleted successfully...";
	}

	public List<Employee> getEmployeeByDesig(String desig){
		return dao.getByDesignation(desig);
	}
	
	public List<Employee> getEmployeeAbove(int age){
		return dao.findByAgeGreaterThan(age);
	}
	
	public List<Employee> getEmployeeBelow(int age){
		return dao.findByAgeLessThan(age);
	}
	
	public List<Employee> getCustomQuery(String desig){
		return dao.myCustomQuery(desig);
	}
}
