package com.example.demo.models;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.models.lappy.Laptop;

@Component("emp")
//@Lazy
@Scope("prototype")
public class Employee implements Person {
	@Value("25")
	private int age;
	@Value("Crystal")
	private String name;
	@Value("30000")
	private int salary;
	@Value("Tester")
	private String designation;
	@Autowired
	private Address address;
	@Autowired
	//@Qualifier("mac")
	private Laptop laptop;
	
	public Employee() {
		System.out.println("Emp() object created here...");
	}
	
	@Override
	public String toString() {
		System.out.println("Address : "+address);
		System.out.println("Laptop : "+laptop);
		return "Employee[name = "+name+", Age = "+age+", Salary = "+salary+", Designation = "+designation+"]";
	}
}
