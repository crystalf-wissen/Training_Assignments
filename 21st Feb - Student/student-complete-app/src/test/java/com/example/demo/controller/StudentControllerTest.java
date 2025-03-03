package com.example.demo.controller;

import com.example.demo.models.Student;

import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean 
    private StudentService studentService;

    private Student student;

    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        student = new Student(101, "John Doe", 10, "DPS", 35.5);
//    }
   
    @Test
	public void testGetStudents() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/students")).andExpect(MockMvcResultMatchers.status().is(200));
	}
    
    @Test
    public void testGetSpecificStudentByRollNo() throws Exception {
        Mockito.when(studentService.getSpecificRollNo(101)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/101")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.name").value("John Doe"));
    }
    
    @Test
    public void testGetSpecificSchool() throws Exception {
    	List<Student> students = Arrays.asList(student);
        Mockito.when(studentService.getSpecificSchool("DPS")).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/school").param("name", "DPS")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$[0].school").value("DPS"));
    }
    
    @Test
    public void testDeleteStudent() throws Exception {
    	Mockito.when(studentService.deleteStudent(101)).thenReturn("Student Deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/101")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().string("Student Deleted"));
    }
    
    @Test
    public void testGetTopStandardWise() throws Exception {
        List<Student> students = Arrays.asList(student);
        Mockito.when(studentService.getTopStandardWise(10)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/students/topper/10")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.size()").value(1)).andExpect(jsonPath("$[0].name").value("John Doe")).andExpect(jsonPath("$[0].standard").value(10));
    }
    
    @Test
    public void testGetStudentsByStandard() throws Exception {
    	Mockito.when(studentService.getStudentsByStandard(10)).thenReturn(15L);

        mockMvc.perform(MockMvcRequestBuilders.get("/students/10/count")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().string("15"));
    }
    
    @Test
    public void testGetStrength() throws Exception {
    	Mockito.when(studentService.getStrength("DPS")).thenReturn(100L);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/strength").param("school", "DPS")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().string("100"));
    }
   
}
