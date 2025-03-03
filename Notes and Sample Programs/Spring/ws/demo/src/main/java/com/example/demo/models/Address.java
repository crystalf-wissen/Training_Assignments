package com.example.demo.models;

import org.springframework.stereotype.Component;

@Component
public class Address {
	private String state;
	private String city;
	private int pin;
	@Override
	public String toString() {
		return "Address [state=" + state + ", city=" + city + ", pin=" + pin + "]";
	}
}
