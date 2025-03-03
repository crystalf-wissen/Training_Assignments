package com.example.demo.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.example.demo.exception.InvalidAgeException;

public class Age {
    public static int readAge(int min,int max){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter your age: ");
            try {
                int age = sc.nextInt();
                if(age<min || age>max){
                    throw new InvalidAgeException();
                }
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Please enter a number only.");
                sc.nextLine();
            } catch (InvalidAgeException e) {
                e.displayMessage();
            }
        }
    }
}