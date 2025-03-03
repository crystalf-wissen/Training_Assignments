package com.example.demo.controller;

import com.example.demo.models.ClassTeacher;
import com.example.demo.models.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    //create
    @PostMapping("/students")
    public String createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // update
    @PutMapping("/students/{rollNo}")
    public Student updateStudent(@PathVariable int rollNo, @RequestBody Student student) {
        return studentService.updateStudent(rollNo, student);
    }

    // delete
    @DeleteMapping("/students/{rollNo}")
    public String deleteStudent(@PathVariable int rollNo) {
        return studentService.deleteStudent(rollNo);
    }

    // /students - get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // /student/rollNo - get specific student with given rollNo
    @GetMapping("/student/{rollNo}")
    public Optional<Student> getSpecificRollNo(@PathVariable int rollNo) {
        return studentService.getSpecificRollNo(rollNo);
    }

    // /students/school?name=DPS - get all students of that school
    @GetMapping("/students/school")
    public List<Student> getSpecificSchool(@RequestParam String name) {
        return studentService.getSpecificSchool(name);
    }

    // /students/result?pass=true/false - all students above 40% / below 40%
    @GetMapping("/students/result")
    public List<Student> getResults(@RequestParam boolean pass) {
        return studentService.getResults(pass);
    }

    // /students/5/count - how many students in 5th standard
    @GetMapping("/students/{standard}/count")
    public Long getStudentsByStandard(@PathVariable int standard) {
        return studentService.getStudentsByStandard(standard);
    }

    // /students/strength?school=DPS - total strength for given school name
    @GetMapping("/students/strength")
    public Long getStrength(@RequestParam String school) {
        return studentService.getStrength(school);
    }

    // /students/toppers - top 5 percentage students
    @GetMapping("/students/toppers")
    public List<Student> getTopFive() {
        return studentService.getTopFive();
    }

    // /students/topper/3 - topper of 3rd standard
    @GetMapping("/students/topper/{standard}")
    public List<Student> getTopStandardWise(@PathVariable int standard) {
        return studentService.getTopStandardWise(standard);
    }
    
    // -/students/class_teacher?regNo=11025
    @GetMapping("/students/class_teacher")
    public List<ClassTeacher> getClassTeacher(@RequestParam int regNo) {
        return studentService.getClassTeacherOfStudentByStandard(regNo);
    }

}
