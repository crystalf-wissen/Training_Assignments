package com.example.demo.models;

public class Programmer extends Emp {
    public Programmer(int id, String name, int age) {
        super(id, name, age, "Programmer", 30000);
    }

    @Override
    public void displayDetails() {
        System.out.println("Programmer Details: ID: " + getId() + ", Name: " + getName() + ", Age: " + getAge() + ", Salary: " + getSalary());
    }
}