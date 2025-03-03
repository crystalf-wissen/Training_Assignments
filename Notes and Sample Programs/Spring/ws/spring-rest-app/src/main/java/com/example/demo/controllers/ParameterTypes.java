package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;

@RestController
public class ParameterTypes {
	@GetMapping("/query")
	public String queryParamDemo(@RequestParam String name,@RequestParam int age) {
		System.out.println("***Query Parameter***");
		return "Welcome! You are "+name+" and of age "+age;
	}
	@GetMapping("/path/{name}/{age}")
	public String pathParamDemo(@PathVariable String name,@PathVariable int age) {
		System.out.println("***Path Parameter***");
		return "Welcome! You are "+name+" and of age "+age;
	}
	@GetMapping("/body")
	public String bodyParamDemo(@RequestBody Person p) {
		System.out.println("***Body Parameter***");
		return "Welcome! You are "+p.getName()+" and of age "+p.getAge();
	}
	@GetMapping(path="/persons",produces="application/xml")
	public List<Person> getPersons(){
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Crystal",21));
		list.add(new Person("Carol",19));
		list.add(new Person("Carina",19));
		return list;		
	}
}
