package com.example.demo.models;

public class Manager extends Emp {
    public Manager(int id, String name, int age) {
        super(id, name, age, "Manager", 50000);
    }

    @Override
    public void displayDetails() {
        System.out.println("Manager Details: ID: " + getId() + ", Name: " + getName() + ", Age: " + getAge() + ", Salary: " + getSalary());
    }
}