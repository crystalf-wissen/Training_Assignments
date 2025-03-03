package com.example.demo.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.exception.InvalidNameException;

public class Name {
    public static String readName(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter your Name: ");
            try {
                String name = sc.nextLine();
                Pattern p1 = Pattern.compile("[A-Z][a-z]*\\s[A-Z][a-z]*");
                Matcher m1 = p1.matcher(name);
                if(!m1.matches()){
                    if(name.split("\\s+").length!=2) {
                        throw new InvalidNameException("Name must contain exactly two words");
                    } else{
                        throw new InvalidNameException("Must have both names properly capitalized and contain only alphabets (e.g. Crystal Fernandes)");
                    }
                }
                return name;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}