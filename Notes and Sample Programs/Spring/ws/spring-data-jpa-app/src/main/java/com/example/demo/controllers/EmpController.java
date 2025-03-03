//package com.example.demo.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.entities.Employee;
//import com.example.demo.repos.EmpDao;
//
//@RestController
//public class EmpController {
//	
//	@Autowired
//	EmpDao dao;
//	
//	@GetMapping("/employees")
//	public Iterable<Employee> getEmployees(){
//		return dao.findAll();
//	}
//	
//	@PostMapping("/employees")
//	public String addEmployees(@RequestBody Employee e){
//		if(dao.existsById(e.getEid()))
//			return "Sorry! ID already exists";
//		dao.save(e);
//		return "Employee saved successfully...";
//	}
//	
//	@RequestMapping(path="/employees/{id}", method = {RequestMethod.PUT,RequestMethod.PATCH})
//	public String updateEmployees(@RequestBody Employee e, @PathVariable int id){
//		if(!dao.existsById(id))
//			return "Sorry! No employee with given ID exists to update";
//		dao.save(e);
//		return "Updated employee record successfully...";
//	}
//	
//	@DeleteMapping("/employees/{id}")
//	public String addEmployees(@PathVariable int id){
//		if(!dao.existsById(id))
//			return "Sorry! Employee doesn't exist";
//		dao.deleteById(id);
//		return "Employee deleted successfully...";
//	}
//}
