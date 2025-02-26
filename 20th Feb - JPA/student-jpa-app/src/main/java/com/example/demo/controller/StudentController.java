package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public String saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return "Student saved in both databases!";
    }
}
