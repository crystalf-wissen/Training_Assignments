package com.example.demo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.config.AppConfig;
import com.example.demo.exception.CEOExistsException;
import com.example.demo.models.Emp;
import com.example.demo.service.EmpFactory;

import java.util.Scanner;

@Component
public class EmpManageApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmpFactory empFactory = context.getBean(EmpFactory.class);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter employee type (CEO, Clerk, Programmer, Manager): ");
                    String type = sc.next();
                    try {
                        Emp emp = empFactory.createEmployee(type);
                        if (emp != null) {
                            emp.display();
                        }
                    } catch (CEOExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Employees will be displayed through Spring Beans");
                    break;

                case 3:
                    System.out.println("Salary Raised for all Employees.");
                    break;
            }
        } while (choice != 4);

        sc.close();
    }
}
