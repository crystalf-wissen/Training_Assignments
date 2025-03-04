package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.example.demo.models.Clerk;
import com.example.demo.models.Emp;
import com.example.demo.models.Manager;
import com.example.demo.models.Programmer;

@Service
public class EmployeeService {

    private List<Emp> employees = new ArrayList<>();
    private AtomicInteger nextEmployeeId = new AtomicInteger(1);

    public Emp addEmployee(Emp emp) {
        emp.setId(nextEmployeeId.getAndIncrement());
        employees.add(emp);
        return emp;
    }

    public Emp searchEmployee(int id) {
        for (Emp employee : employees) {
            if (employee.getId() == id) {
                employee.displayDetails();
                return employee;
            }
        }
        return null;
    }

    public List<Emp> displayEmployees() {
        for (Emp emp : employees) {
            emp.displayDetails();
        }
        return employees;
    }

    public boolean removeEmployee(int id) {
        int removeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex != -1) {
            employees.remove(removeIndex);
            return true;
        } else {
            return false;
        }
    }

    public Emp updateEmployeeSalary(int id, double newSalary) {
        Emp employee = searchEmployee(id);
        if (employee != null) {
            employee.setSalary(newSalary);
            return employee;
        }
        return null; 
    }

    public Emp createEmployee(String name, int age, String designation) {
        int dummyId = 0;
        if ("clerk".equalsIgnoreCase(designation)) {
            return new Clerk(dummyId, name, age);
        } else if ("programmer".equalsIgnoreCase(designation)) {
            return new Programmer(dummyId, name, age);
        } else if ("manager".equalsIgnoreCase(designation)) {
            return new Manager(dummyId, name, age);
        } else {
            return null;
        }
    }
}