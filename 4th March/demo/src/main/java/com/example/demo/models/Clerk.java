package com.example.demo.models;

public class Clerk extends Emp {
    public Clerk(int id, String name, int age) {
        super(id, name, age, "Clerk", 20000);
    }

    @Override
    public void displayDetails() {
        System.out.println("Clerk Details: ID: " + getId() + ", Name: " + getName() + ", Age: " + getAge() + ", Salary: " + getSalary());
    }
}