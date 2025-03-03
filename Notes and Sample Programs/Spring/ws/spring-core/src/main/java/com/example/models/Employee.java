package com.example.models;

import org.springframework.stereotype.Component;

//simple but less control
@Component
public class Employee {
	private String name;
	private int age;
	private int salary;
	private String designation;
	private Address address;
	
	public Employee() {
		System.out.println("Employee() object created...");
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Employee(String name, int age, int salary, String designation, Address address) {
		
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.designation = designation;
		this.address = address;
	}

	@Override
	public String toString() {
		System.out.println("Address "+address);
		return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + ", designation=" + designation + "]";
	}
	
	
}
