package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Emp;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("get/{id}")
    public List<Emp> getEmployeeById(@PathVariable int id) {
        Emp employee = employeeService.searchEmployee(id);
        if (employee == null) {
            return new ArrayList<>();
        } else {
            List<Emp> employeeList = new ArrayList<>();
            employeeList.add(employee);
            return employeeList;
        }
    }
    @PostMapping("create")
    public String addEmployee(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        int age = Integer.parseInt(requestBody.get("age").toString());
        String designation = (String) requestBody.get("designation");
        Emp employee = employeeService.createEmployee(name, age, designation);
        Emp addedEmployee = employeeService.addEmployee(employee);
        return "Employee added successfully with ID: " + addedEmployee.getId();
    }

    @DeleteMapping("delete/{id}")
    public String removeEmployee(@PathVariable int id) {
        boolean removed = employeeService.removeEmployee(id);
        if (!removed) {
            return "Employee with ID: " + id + " not found.";
        } else {
            return "Employee with ID: " + id + " removed successfully.";
        }
    }

    @GetMapping("getall")
    public List<Emp> getAllEmployees() {
        return employeeService.displayEmployees();
    }

}