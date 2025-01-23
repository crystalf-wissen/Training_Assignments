import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.Map.Entry;

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
    public static int readAge(int min, int max) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your age: ");
            try {
                int age = Integer.parseInt(br.readLine());
                if (age < min || age > max) {
                    throw new InvalidAgeException();
                }
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Please enter a number only.");
            } catch (InvalidAgeException e) {
                e.displayMessage();
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
    }
}

class EID {
    private static HashMap<Integer, Emp> empMap;

    public static void setEmpMap(HashMap<Integer, Emp> map) {
        empMap = map;
    }

    public static int readEID() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter the EID: ");
            try {
                int eid = Integer.parseInt(br.readLine());
                if (empMap.containsKey(eid))
                    throw new InvalidEIDException();
                return eid;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Please enter a number only.");
            } catch (InvalidEIDException e) {
                e.displayMessage();
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
    }

    public static void searchEID(HashMap<Integer, Emp> empMap) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the EID to search: ");
        
        try {
            int eid = Integer.parseInt(br.readLine());
            if (empMap.containsKey(eid)) {
                Emp emp = empMap.get(eid);
                emp.display();
            } else {
                System.out.println("Employee with EID " + eid + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid format. Please enter a number only.");
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }
}

class Name {
    public static String readName() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your Name: ");
            try {
                String name = br.readLine();
                Pattern p1 = Pattern.compile("[A-Z][a-z]*\\s[A-Z][a-z]*");
                Matcher m1 = p1.matcher(name);
                if (!m1.matches()) {
                    if (name.split("\\s+").length != 2) {
                        throw new InvalidNameException("Name must contain exactly two words");
                    } else {
                        throw new InvalidNameException("Must have both names properly capitalized and contain only alphabets (e.g. Crystal Fernandes)");
                    }
                }
                return name;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
    }

    public static void searchName(HashMap<Integer, Emp> empMap) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name to search: ");
        String name = null;
        try{
            name = br.readLine();
        }catch(Exception e){
            e.printStackTrace();
        }

        boolean found = false;
        for (Emp emp : empMap.values()) {
            if (emp.name.equalsIgnoreCase(name)) {
                emp.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No employee with name " + name + " found.");
        }
    }
}

class Designation {
    public static void searchDesignation(HashMap<Integer, Emp> empMap) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the designation to search: ");
        String designation = null;
        try {
            designation = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        boolean found = false;
        for (Emp emp : empMap.values()) {
            if (emp.designation.equalsIgnoreCase(designation)) {
                emp.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No employee with designation " + designation + " found.");
        }
    }
}

class Menu {
    private static int maxChoice;

    public static int readChoice(int max) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maxChoice = max;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(br.readLine());
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid format. Please enter a number only.");
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
    }
}

class CSV {
    
    public static void updateEmployeeToCSV(HashMap<Integer, Emp> empMap) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("employees.csv", false));
        for (Emp emp : empMap.values()) {
            pw.println(emp);
        }
        pw.flush();
        pw.close();
    }
    
    public static HashMap<Integer, Emp> readAllEmployeesFromCSV() throws IOException {
        HashMap<Integer, Emp> empMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("employees.csv"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            int eid = Integer.parseInt(fields[0]);
            String name = fields[1];
            int age = Integer.parseInt(fields[2]);
            float salary = Float.parseFloat(fields[3]);
            String designation = fields[4];
            
            Emp emp = null;
            switch (designation) {
                case "Clerk":
                    emp = new Clerk(eid, name, age, salary, designation);
                    break;
                case "Programmer":
                    emp = new Programmer(eid, name, age, salary, designation);
                    break;
                case "Manager":
                    emp = new Manager(eid, name, age, salary, designation);
                    break;
                case "CEO":
                    emp = CEO.getCEO(eid, name, age, salary, designation);
                    break;
                default:
                    System.out.println("Unknown designation: " + designation);
                    continue;
            }
            empMap.put(eid, emp);
        }
        br.close();
        return empMap;
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
        name = Name.readName();
        age = Age.readAge(21, 60);
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;
        eid = EID.readEID();
    }

    Emp(int eid, String name, int age, float salary, String designation) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
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

    public static boolean removeEmployee(HashMap<Integer, Emp> empMap, int eidToRemove) {
        if (empMap.containsKey(eidToRemove)) {
            empMap.remove(eidToRemove);
            System.out.println("Employee with EID " + eidToRemove + " has been removed.");
            try {
                CSV.updateEmployeeToCSV(empMap);   
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("No employee found with EID " + eidToRemove);
            return false;
        }
    }
    public String toString() {
        return eid+","+name+","+age+","+salary+","+designation;
    }
}

class Clerk extends Emp {
    Clerk() {
        super(20000, "Clerk");
    }

    Clerk(int eid, String name, int age, float salary, String designation) {
        super(eid, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 2000;
    }
}

class Programmer extends Emp {
    Programmer() {
        super(30000, "Programmer");
    }

    Programmer(int eid, String name, int age, float salary, String designation) {
        super(eid, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 5000;
    }
}

class Manager extends Emp {
    Manager() {
        super(100000, "Manager");
    }

    Manager(int eid, String name, int age, float salary, String designation) {
        super(eid, name, age, salary, designation);
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

    private CEO(int eid, String name, int age, float salary, String designation) {
        super(eid, name, age, salary, designation);
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

    public static CEO getCEO(int eid, String name, int age, float salary, String designation) {
        if (ceo1 == null) {
            ceo1 = new CEO(eid, name, age, salary, designation);
        }
        return ceo1;
    }
}

class EmpFactory {
    private static boolean ceoCreated = false;

    public static Emp createEmployee(String type) {
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

class IDSorter implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Integer.compare(e1.eid, e2.eid);
    }
}

class NameSorter implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e1.name.compareTo(e2.name);
    }
}

class AgeSorter implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Integer.compare(e1.age, e2.age);
    }
}

class SalarySorter implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Float.compare(e1.salary, e2.salary);
    }
}

class DesignationSorter implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e1.designation.compareTo(e2.designation);
    }
}

public class EmpManageApp {
    public static void main(String[] args) {
        int ch1 = 0, ch2 = 0, ch3 = 0;
        HashMap<Integer, Emp> empMap = new HashMap<>();
        EID.setEmpMap(empMap);

        try {
            empMap = CSV.readAllEmployeesFromCSV();
        } catch (IOException e) {
            System.out.println("Error reading employees from CSV: " + e.getMessage());
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
                                case 1 -> newEmp = EmpFactory.createEmployee("CEO");
                                case 2 -> newEmp = EmpFactory.createEmployee("Clerk");
                                case 3 -> newEmp = EmpFactory.createEmployee("Programmer");
                                case 4 -> newEmp = EmpFactory.createEmployee("Manager");
                            }
                            
                            empMap.put(newEmp.eid, newEmp);
                            CSV.updateEmployeeToCSV(empMap);
                          
                        } catch (CEOExistsException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } while (ch2 != 5);
                    break;

                case 2:
                    File f = new File("employees.csv");
                    if (!f.exists()) {
                        System.out.println("No Employee Present to Display");
                    } else {
                        System.out.println("Display By: ");
                        do {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. ID");
                            System.out.println("2. Designation");
                            System.out.println("3. Name");
                            System.out.println("4. Age");
                            System.out.println("5. Salary");
                            System.out.println("6. Back");
                            System.out.println("--------------------------------------------");
                            ch3 = Menu.readChoice(6);

                            try {
                                List<Emp> empList = new ArrayList<>(empMap.values());
                                
                                switch (ch3) {
                                    case 1 -> Collections.sort(empList, new IDSorter());
                                    case 2 -> Collections.sort(empList, new DesignationSorter());
                                    case 3 -> Collections.sort(empList, new NameSorter());
                                    case 4 -> Collections.sort(empList, new AgeSorter());
                                    case 5 -> Collections.sort(empList, new SalarySorter());

                                }
                                for (Emp emp : empList) {
                                    emp.display();
                                }
                            } catch (CEOExistsException e) {
                                System.out.println(e.getMessage());
                            } 
                        } while (ch3 != 6);
                    }
                    break;

                    case 3:
                    if (empMap.size() == 0) {
                        System.out.println("No Employee Present to Raise Salary");
                    } else {
                        for (Emp emp : empMap.values()) {
                            emp.raiseSalary();
                            try {
                                CSV.updateEmployeeToCSV(empMap);
                                System.out.println("Updated salary ");
                            } catch (IOException e) {
                                System.out.println("Error writing employee to CSV: " + e.getMessage());
                            }
                        }
                    }
                    break;
                
                case 4:
                    System.out.println("Enter the EID of the employee to remove: ");
                    int eidToRemove=-1;
                    try{
                        eidToRemove = Integer.parseInt(br.readLine());
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    boolean removed = Emp.removeEmployee(empMap, eidToRemove);

                    if (!removed) {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 5:
                    System.out.println("Search By: ");
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("1. ID");
                        System.out.println("2. Designation");
                        System.out.println("3. Name");
                        System.out.println("4. Back");
                        System.out.println("--------------------------------------------");
                        ch3 = Menu.readChoice(4);

                        switch (ch3) {
                            case 1 -> EID.searchEID(empMap);
                            case 2 -> Designation.searchDesignation(empMap);
                            case 3 -> Name.searchName(empMap);
                        }
                    } while (ch3 != 4);
                    break;
            }
        } while (ch1 != 6);

        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }
}
