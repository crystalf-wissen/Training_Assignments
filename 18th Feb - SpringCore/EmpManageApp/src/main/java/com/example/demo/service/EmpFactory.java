package com.example.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.exception.CEOExistsException;
import com.example.demo.models.CEO;
import com.example.demo.models.Clerk;
import com.example.demo.models.Emp;
import com.example.demo.models.Manager;
import com.example.demo.models.Programmer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.stereotype.Service;
import com.example.demo.models.Emp;
import com.example.demo.exception.CEOExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpFactory {
    private static boolean ceoCreated = false;
    private final List<Emp> employees = new ArrayList<>();

    public Emp createEmployee(String type) {
        Emp newEmployee = null;

        if (!ceoCreated && !"CEO".equalsIgnoreCase(type)) {
            System.out.println("You must create the CEO before other employees!");
            return null;
        }

        switch (type.toLowerCase()) {
            case "clerk":
                newEmployee = new Clerk();
                break;
            case "programmer":
                newEmployee = new Programmer();
                break;
            case "manager":
                newEmployee = new Manager();
                break;
            case "ceo":
                if (ceoCreated) {
                    throw new CEOExistsException("CEO already exists");
                }
                newEmployee = CEO.getInstance();
                ceoCreated = true;
                break;
            default:
                System.out.println("Invalid employee type.");
                return null;
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    public List<Emp> getEmployees() {
        return employees;
    }
}
