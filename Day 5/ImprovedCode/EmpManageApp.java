import java.util.*;
import java.util.regex.*;

class InvalidChoiceException extends RuntimeException {
    public InvalidChoiceException() {
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }

    public void displayMessage(int maxChoice) {
        System.out.println("Please enter a choice between 1 and " + maxChoice);
    }
}

class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
    }

    public InvalidAgeException(String msg) {
        super(msg);
    }
    public void displayMessage() {
        System.out.println("Please enter age between 21 and 60");
    }
}

class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
    }

    public InvalidNameException(String msg) {
        super(msg);
    }
}

class Age {
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

class Name {
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

class Menu {
    private static int maxChoice;
    public static int readChoice(int max) {
        Scanner sc = new Scanner(System.in);
        maxChoice=max;
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

abstract class Emp {
    int eid;
    String name;
    int age;
    float salary;
    String designation;

    static int countEmp = 0;
    static int empIdCounter = 1;

    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
        name = Name.readName();
        age = Age.readAge(21,60);
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;
        this.eid = empIdCounter++;
    }

    public void display() {
        System.out.println("Eid: " + eid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + designation);
        System.out.println();
    }

    public abstract void raiseSalary();
}

class Clerk extends Emp {
    Clerk() {
        super(20000, "Clerk");
    }

    public void raiseSalary() {
        salary += 2000;
    }
}

class Programmer extends Emp {
    Programmer() {
        super(30000, "Programmer");
    }

    public void raiseSalary() {
        salary += 5000;
    }
}

class Manager extends Emp {
    Manager() {
        super(100000, "Manager");
    }

    public void raiseSalary() {
        salary += 15000;
    }
}

public class EmpManageApp {
    public static void main(String[] args) {
        int ch1 = 0, ch2 = 0;
        Emp[] emp = new Emp[100];
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Remove Employee");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------");
            ch1 = Menu.readChoice(5);

            switch (ch1) {
                case 1:
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("1. Create Clerk");
                        System.out.println("2. Create Programmer");
                        System.out.println("3. Create Manager");
                        System.out.println("4. Back");
                        System.out.println("--------------------------------------------");
                        ch2 = Menu.readChoice(4);

                        switch (ch2) {
                            case 1:
                                emp[Emp.countEmp] = new Clerk();
                                break;
                            case 2:
                                emp[Emp.countEmp] = new Programmer();
                                break;
                            case 3:
                                emp[Emp.countEmp] = new Manager();
                                break;
                        }
                    } while (ch2 != 4);
                    break;

                case 2:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Display");
                    }
                    for (int i = 0; i < Emp.countEmp; i++) {
                        emp[i].display();
                    }
                    break;

                case 3:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Raise Salary");
                    }
                    for (int i = 0; i < Emp.countEmp; i++) {
                        emp[i].raiseSalary();
                    }
                    break;
                case 4:
                    System.out.println("Dummy removal");
                    break;
            }
        } while (ch1 != 5);
        sc.close();
        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }
}
