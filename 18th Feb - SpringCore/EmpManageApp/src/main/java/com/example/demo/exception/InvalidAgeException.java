package com.example.demo.exception;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
    }

    public InvalidAgeException(String msg) {
        super(msg);
    }
    public void displayMessage() {
        System.out.println("Please enter age between 21 and 60");
    }
}