package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ClassTeacher;
import com.example.demo.models.Student;
import com.example.demo.repository.ClassTeacherRepository;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentrepository;
	
	@Autowired
	ClassTeacherRepository teacherrepository;
	
	// create
    public String createStudent(Student s) {
        if (!studentrepository.existsById(s.getRollNo())) {
        	studentrepository.save(s);
            return "Student with rollNo " + s.getRollNo() + " created successfully.";
        }
        return "Student with rollNo " + s.getRollNo() + " already exists.";
    }
    
    //update
    public Student updateStudent(int rollNo, Student student) {
        if (studentrepository.existsById(rollNo)) {
            student.setRollNo(rollNo); 
            return studentrepository.save(student);
        }
        return null; 
    }
    
    //delete
    public String deleteStudent(int rollNo) {
        if (studentrepository.existsById(rollNo)) {
        	studentrepository.deleteById(rollNo);
            return "Student with rollNo " + rollNo + " deleted successfully.";
        }
        return "Student with rollNo " + rollNo + " not found.";
    }
    
    //	get all students
	public List<Student> getAllStudents() {
		return studentrepository.findAll();
	}
	
	// get specific student with given rollNo
	public Optional<Student> getSpecificRollNo(int rollNo) {
		return studentrepository.findById(rollNo);
	}
	
	// get all students of that school
	public List<Student> getSpecificSchool(String name) {
		return studentrepository.findBySchool(name);
	}
	
	// all students above 40% / below 40%
	public List<Student> getResults(boolean pass) {
		if (pass)
			return studentrepository.findByPercentageGreaterThan(40);
		else
			return studentrepository.findByPercentageLessThan(40);
	}
	
	// how many students in 5th standard
	public Long getStudentsByStandard(int standard) {
			return studentrepository.findByStandard(standard).stream().count();
	}
	
	// total strength for given school name
	public Long getStrength(String school) {
		return studentrepository.findBySchool(school).stream().count();
	}
	
	// top 5 percentage students
	public List<Student> getTopFive() {
		return studentrepository.findAll().stream().sorted((s1, s2) -> Double.compare(s2.getPercentage(), s1.getPercentage())).limit(5).collect(Collectors.toList());
	}
	
	// topper of 3rd standard
	public List<Student> getTopStandardWise(int standard) {
		return studentrepository.findAll().stream().filter(s->s.getStandard()==standard).sorted((s1, s2) -> Double.compare(s2.getPercentage(), s1.getPercentage())).limit(1).collect(Collectors.toList());
	}
	
	// get classteacher of student based on standard 
	public List<ClassTeacher> getClassTeacherOfStudentByStandard(int regNo){
		Optional<Student> s = studentrepository.findById(regNo);
		if(s.isPresent()) {
			int standard = s.get().getStandard();
			return teacherrepository.findByStandard(standard);
		}
		return Collections.emptyList();
	}
}
