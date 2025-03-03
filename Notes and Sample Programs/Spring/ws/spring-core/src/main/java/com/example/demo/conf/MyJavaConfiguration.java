package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.Student;
import com.example.models.Address;
import com.example.models.Employee;

//this is better when you cant touch a class and edit within
//complex but more control and dynamic nature
@Configuration
public class MyJavaConfiguration {
	@Bean
	public Employee getEmployee() {
		return new Employee("Raju",25,30000,"Tester",getAddress()); //local obj so dont write 'new' here
	}
	@Bean
	public Student getStudent() {
		return new Student();
	}
	@Bean
	public Address getAddress() {
		return new Address("xyz","abc",400099);
	}
	@Bean("emp_s")
	public Employee getEmployee2() {
		Employee e = new Employee();
		e.setName("Manisha");
		e.setAge(33);
		e.setSalary(70000);
		e.setDesignation("Programmer");
		e.setAddress(getAddress2());
		return e;
	}
	@Bean("addr_s")
	public Address getAddress2() {
		Address a1 = new Address();
		a1.setState("Maharastra");
		a1.setCity("Pune");
		a1.setPin(32435);
		return a1;
	}
}
