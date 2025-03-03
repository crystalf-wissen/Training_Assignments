package com.example.demo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student implements Person {
	@Value("${sroll}")
	private int rollNo;
	@Value("${sname}")
	private String name;
	@Value("${sstandard}")
	private int standard;
	
	@Override
	public String toString() {
		return "Student[name = "+name+", rollNo = "+rollNo+", Standard = "+standard+"]";
	}
}
