package com.example.demo.models;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.example.demo.util.Age;
import com.example.demo.util.Name;

public abstract class Emp {
    int eid;
    String name;
    int age;
    float salary;
    String designation;

    public static int countEmp = 0;
    static int empIdCounter = 1;

    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
        name = Name.readName();
        age = Age.readAge(21,60);
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;
        this.eid = empIdCounter++;
    }

    public void display() {
        System.out.println("Eid: " + eid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + designation);
        System.out.println();
    }

    public abstract void raiseSalary();
}
