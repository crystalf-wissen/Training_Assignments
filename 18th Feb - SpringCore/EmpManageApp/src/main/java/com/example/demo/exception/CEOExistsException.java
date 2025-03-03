package com.example.demo.exception;

public class CEOExistsException extends RuntimeException {
    public CEOExistsException() {
        super();
    }
    public CEOExistsException(String msg) {
        super(msg);
    }
    public void displayMessage() {
        System.out.println("CEO already exists in the company!");
    }
}