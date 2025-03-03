package com.example.demo.exception;

public class InvalidChoiceException extends RuntimeException {
    public InvalidChoiceException() {
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }

    public void displayMessage(int maxChoice) {
        System.out.println("Please enter a choice between 1 and " + maxChoice);
    }
}