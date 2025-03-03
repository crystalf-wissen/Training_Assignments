package com.example.demo;

import org.springframework.boot.SpringApplication;

import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Person;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		BeanFactory factory = SpringApplication.run(DemoApplication.class, args);
		System.out.println("Welcome to Spring Boot Application");
		System.out.println("Enter which object to create : ");
		String obj = new Scanner(System.in).next();
		Person p1 = (Person) factory.getBean(obj);
		System.out.println(p1);
	}

}
