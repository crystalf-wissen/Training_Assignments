package com.example.demo.models;

public class Student {
	private String name;
	private int rollNo;
	private int standard;
	private String school;
	private double percentage;
	
    public Student(int rollNo, String name, int standard, String school, double percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.standard = standard;
        this.school = school;
        this.percentage = percentage;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", rollNo=" + rollNo + ", standard=" + standard + ", school=" + school
				+ ", percentage=" + percentage + "]";
	}
	
	
}
