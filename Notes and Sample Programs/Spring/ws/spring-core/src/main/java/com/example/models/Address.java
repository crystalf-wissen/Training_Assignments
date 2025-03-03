package com.example.models;

public class Address {
	private String state;
	private String city;
	private int pin;
	
	public Address() {
		System.out.println("Address() object created....");
	}
	
	public Address(String state, String city, int pin) {
		this.state = state;
		this.city = city;
		this.pin = pin;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Address [state=" + state + ", city=" + city + ", pin=" + pin + "]";
	}

}
