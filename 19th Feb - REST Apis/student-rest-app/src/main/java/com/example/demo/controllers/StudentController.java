package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Student;

@RestController
public class StudentController {
	private List<Student> studentList = new ArrayList<>();
	
	public StudentController() {
	    studentList.add(new Student(1, "Alice", 10, "DPS", 85.0));
	    studentList.add(new Student(2, "Bob", 10, "XYZ School", 72.5));
	    studentList.add(new Student(3, "Charlie", 9, "DPS", 88.3));
	    studentList.add(new Student(4, "David", 8, "ABC School", 45.2));
	    studentList.add(new Student(5, "Eva", 5, "DPS", 95.0));
	    studentList.add(new Student(6, "Frank", 5, "XYZ School", 30.5));
	    studentList.add(new Student(7, "Grace", 11, "DPS", 92.0));
	    studentList.add(new Student(8, "Hank", 11, "XYZ School", 67.8));
	    studentList.add(new Student(9, "Ivy", 12, "ABC School", 80.1));
	    studentList.add(new Student(10, "Jack", 12, "DPS", 60.0));
	    studentList.add(new Student(11, "Kara", 11, "XYZ School", 85.5));
	    studentList.add(new Student(12, "Liam", 10, "DPS", 75.4));
	    studentList.add(new Student(13, "Mia", 9, "XYZ School", 95.3));
	    studentList.add(new Student(14, "Nina", 8, "DPS", 50.2));
	    studentList.add(new Student(15, "Oscar", 8, "XYZ School", 58.0));
	    studentList.add(new Student(16, "Paul", 5, "ABC School", 65.4));
	    studentList.add(new Student(17, "Quinn", 12, "XYZ School", 78.6));
	    studentList.add(new Student(18, "Ryan", 10, "ABC School", 40.7));
	    studentList.add(new Student(19, "Sophia", 9, "DPS", 87.9));
	    studentList.add(new Student(20, "Tom", 7, "XYZ School", 82.0));
	    studentList.add(new Student(21, "Uma", 7, "DPS", 55.0));
	    studentList.add(new Student(22, "Vera", 11, "DPS", 91.4));
	    studentList.add(new Student(23, "Will", 11, "ABC School", 70.0));
	    studentList.add(new Student(24, "Xander", 12, "DPS", 66.0));
	    studentList.add(new Student(25, "Yara", 10, "XYZ School", 82.5));
	    studentList.add(new Student(26, "Zane", 9, "ABC School", 76.3));
	}
    
    //	/students			 - get all students
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentList;
	}
	
	// /student/rollNo			 - get specific student with given rollNo
	@GetMapping("/student/{rollNo}")
	public List<Student> getSpecificRollNo(@PathVariable int rollNo) {
		return studentList.stream().filter(s->s.getRollNo()==rollNo).collect(Collectors.toList());
	}
	
	// /students/school?name=DPS	 - get all students of that school
	@GetMapping("/students/school")
	public List<Student> getSpecificSchool(@RequestParam String name) {
		return studentList.stream().filter(s->s.getSchool().equals(name)).collect(Collectors.toList());
	}
	
	//	/students/result?pass=true/false - all students above 40% / below 40%
	@GetMapping("/students/result")
	public List<Student> getResults(@RequestParam boolean pass) {
		if (pass)
			return studentList.stream().filter(s->s.getPercentage()>40).collect(Collectors.toList());
		else
			return studentList.stream().filter(s->s.getPercentage()<40).collect(Collectors.toList());
	}
	
	// /students/5/count		 - how many students in 5th standard
	@GetMapping("/students/{standard}/count")
	public Long getStudentsByStandard(@PathVariable int standard) {
			return studentList.stream().filter(s->s.getStandard()==standard).collect(Collectors.counting());
	}
	
	//	/students/strength?school=DPS	 - total strength for given school name
	@GetMapping("/students/strength")
	public Long getStrength(@RequestParam String school) {
		return studentList.stream().filter(s->s.getSchool().equals(school)).collect(Collectors.counting());
	}
	
	//	/students/toppers		 - top 5 percentage students
	@GetMapping("/students/toppers")
	public List<Student> getTopFive() {
		return studentList.stream().sorted((s1, s2) -> Double.compare(s2.getPercentage(), s1.getPercentage())).limit(5).collect(Collectors.toList());
	}
	
	// /students/topper/3		 - topper of 3rd standard
	@GetMapping("/students/topper/{standard}")
	public List<Student> getTopStandardWise(@PathVariable int standard) {
		return studentList.stream().filter(s->s.getStandard()==standard).sorted((s1, s2) -> Double.compare(s2.getPercentage(), s1.getPercentage())).limit(1).collect(Collectors.toList());
	}
	
}
