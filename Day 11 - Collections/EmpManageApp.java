import java.util.*;
import java.util.regex.*;
import java.util.Collection.*;

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

class InvalidEIDException extends RuntimeException {
    public InvalidEIDException() {
    }

    public InvalidEIDException(String msg) {
        super(msg);
    }
    public void displayMessage() {
        System.out.println("EID already taken");
    }
}

class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
    }

    public InvalidNameException(String msg) {
        super(msg);
    }
}

class CEOExistsException extends RuntimeException {
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

class EID {
    private static HashMap empMap;

    public static void setEmpMap(HashMap map) {
        empMap = map;
    }

    public static int readEID(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter the EID: ");
            try {
                int eid = sc.nextInt();
                if (empMap.containsKey(eid))
                    throw new InvalidEIDException();
                return eid;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Please enter a number only.");
                sc.nextLine();
            } catch (InvalidEIDException e) {
                e.displayMessage();
            }
        }
    }

    public static void searchEmployee(HashMap empMap) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the EID to search: ");
        int eid = sc.nextInt();
        try {
            if (empMap.containsKey(eid)) {
                Emp emp =(Emp) empMap.get(eid);
                emp.display();
            } else {
                System.out.println("Employee with EID " + eid + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid format. Please enter a number only.");
            sc.nextLine();
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

    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
        name = Name.readName();
        age = Age.readAge(21,60);
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;
        eid = EID.readEID();
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

final class CEO extends Emp {
    static private CEO ceo1 = null;

    private CEO() {
        super(500000, "CEO");
    }

    public void raiseSalary() {
        salary += 50000;
    }

    public static CEO getCEO() {
        if (ceo1 == null) {
            ceo1 = new CEO();
        }
        return ceo1;
    }
}

class EmpFactory {
    private static boolean ceoCreated = false;
	public static Emp createEmployee(String type){
        Emp newEmployee = null;

        if (!ceoCreated && !"CEO".equalsIgnoreCase(type)) {
            System.out.println("You must create the CEO before creating other employees!");
            return null;
        }

        if ("Clerk".equalsIgnoreCase(type)) {
            newEmployee = new Clerk();
        } else if ("Programmer".equalsIgnoreCase(type)) {
            newEmployee = new Programmer();
        } else if ("Manager".equalsIgnoreCase(type)) {
            newEmployee = new Manager();
        } else if ("CEO".equalsIgnoreCase(type)) {
            if (ceoCreated) {
                throw new CEOExistsException("CEO already exists");
            }
            newEmployee = CEO.getCEO();
            ceoCreated = true;
        }
	
    return newEmployee;
}
}

public class EmpManageApp {
    public static void main(String[] args) {
        int ch1 = 0, ch2 = 0;
        HashMap empMap = new HashMap();
        EID.setEmpMap(empMap);

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Raise Salary");
            System.out.println("4. Remove Employee");
            System.out.println("5. Search");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");
            ch1 = Menu.readChoice(6);

            switch (ch1) {
                case 1:
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("1. Create CEO");
                        System.out.println("2. Create Clerk");
                        System.out.println("3. Create Programmer");
                        System.out.println("4. Create Manager");
                        System.out.println("5. Back");
                        System.out.println("--------------------------------------------");
                        ch2 = Menu.readChoice(5);

                        try {
                            Emp newEmp = null;
                            switch (ch2) {
                                case 1:
                                    newEmp = EmpFactory.createEmployee("CEO");
                                    break;
                                case 2:
                                    newEmp = EmpFactory.createEmployee("Clerk");
                                    break;
                                case 3:
                                    newEmp = EmpFactory.createEmployee("Programmer");
                                    break;
                                case 4:
                                    newEmp = EmpFactory.createEmployee("Manager");
                                    break;
                            }
                            if(newEmp != null) {
                                empMap.put(newEmp.eid,newEmp);
                            }
                        } catch (CEOExistsException e) {
                            System.out.println(e.getMessage());
                        }

                    } while (ch2 != 5);
                    break;

                case 2:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Display");
                    } else{
                        Set s = empMap.entrySet();
                        Iterator mi = s.iterator();
                        while(mi.hasNext())
                        {
                            Map.Entry entry = (Map.Entry) mi.next();
                            Emp emp = (Emp) entry.getValue();
                            emp.display(); 
                        }
                    }
                    break;

                case 3:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Display");
                    } else{
                        Set s = empMap.entrySet();
                        Iterator mi = s.iterator();
                        while(mi.hasNext())
                        {
                            Map.Entry entry = (Map.Entry) mi.next();
                            Emp emp = (Emp) entry.getValue();
                            emp.raiseSalary(); 
                        }
                    }
                    break;

                case 4:
                    System.out.println("Dummy removal");
                    break;
                
                case 5:
                    EID.searchEmployee(empMap);
                    break;
            }
        } while (ch1 != 6);

        sc.close();
        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }

}
