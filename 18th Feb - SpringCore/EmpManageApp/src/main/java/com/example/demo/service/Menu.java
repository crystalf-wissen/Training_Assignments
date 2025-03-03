package com.example.demo.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidChoiceException;

@Service
public class Menu {
    
    private int maxChoice;

    public int readChoice(int max) {
        maxChoice = max;
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int choice = sc.nextInt();
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Please enter a number only.");
                sc.nextLine();
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
            }
        }
    }
}
