package com.example.demo.models.lappy;

import org.springframework.stereotype.Component;
import jakarta.annotation.Priority;

@Component("mac")
//@Primary
@Priority(value = 1)
public class MacBook implements Laptop {
	public String toString() {
		return "Hey! You got an APPLE MACBOOK";
	}
}
