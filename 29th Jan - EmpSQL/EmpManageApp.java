import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.Map.Entry;
import java.sql.*;

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
}

class EID {
    public static int readEID() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter the EID: ");
            int eid = 0;
            try(
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM emp WHERE eid = ?")
            ) {
                eid = Integer.parseInt(br.readLine());
                pstmt.setInt(1, eid);
                try(ResultSet rs = pstmt.executeQuery()){
                    if(rs.next())
                        throw new InvalidEIDException();
                    return eid;
                  }catch(SQLException e){
                      System.out.println("SQL Exception"+e.getMessage());
                  }
            } catch (Exception e) {
                System.out.println(e);
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
        name = Name.readName();
        age = Age.readAge(21, 60);
        this.salary = salary;
        this.designation = designation;
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

class EmpFactory {
    private static boolean ceoCreated = false;

    public static Emp createEmployee(String type) {
        Emp newEmployee = null;

        if ("Clerk".equalsIgnoreCase(type)) {
            newEmployee = new Clerk();
        } else if ("Programmer".equalsIgnoreCase(type)) {
            newEmployee = new Programmer();
        } else if ("Manager".equalsIgnoreCase(type)) {
            newEmployee = new Manager();
        }
        return newEmployee;
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
            } catch (Exception e) {
                System.out.println("Error reading input.");
            }
        }
    }
}


class DB{
    public static void createNew(Emp newEmp){
        try(
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into emp values(?,?,?,?,?)");
            ) {
            
                pstmt.setInt(1, newEmp.eid);      
                pstmt.setString(2, newEmp.name);  
                pstmt.setInt(3, newEmp.age);    
                pstmt.setFloat(4, newEmp.salary);    
                pstmt.setString(5, newEmp.designation);
                
                pstmt.execute();
                System.out.println("Employee Saved!");
       
            }         
         catch (Exception e) {
            System.out.println(e);
        }
    }   
    public static void searchEmp(int option,String param) {
        try (
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
        ) {
            String query = "";
            PreparedStatement pstmt = null;

            switch (option) {
                case 1:
                    query = "select * from emp where eid = ?";
                    try {
                        pstmt = con.prepareStatement(query);
                        int eid = Integer.parseInt(param);
                        pstmt.setInt(1, eid);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    query = "select * from emp where designation = ?";
                    try {
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1, param);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;


                case 3:
                    query = "select * from emp where name = ?";
                    try {
                        pstmt = con.prepareStatement(query);
                        pstmt.setString(1, param);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;


                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    return;
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int id = rs.getInt("eid");
                    String empName = rs.getString("name");
                    String empDesignation = rs.getString("designation");
                    int empAge = rs.getInt("age");
                    double empSalary = rs.getDouble("salary");

                    System.out.println("Employee ID: " + id);
                    System.out.println("Name: " + empName);
                    System.out.println("Designation: " + empDesignation);
                    System.out.println("Age: " + empAge);
                    System.out.println("Salary: " + empSalary);
                    System.out.println("---------------------------------------------");
                }
                if (!found) {
                    System.out.println("No employee found with the given criteria.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteEmp(int eid) {
        try (
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
        ) {
            String query = "delete from emp where eid = ?";
            
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, eid); 

                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("Employee with ID " + eid + " has been successfully deleted.");
                } else {
                    System.out.println("No employee found with ID " + eid + ". Deletion failed.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void raiseEmp(int eid, float salary){
        
    }
    
}

public class EmpManageApp {
    public static void main(String[] args) {
        int ch1 = 0, ch2 = 0, ch3 = 0;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display");
            System.out.println("3. Appraisal");
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
                            if(newEmp!=null){
                             DB.createNew(newEmp);
                            }
                          
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (ch2 != 5);
                    break;

                case 2:
                    try(
                        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
                        Statement stmt = con.createStatement();
                        ){
                            ResultSet rs = stmt.executeQuery("select * from emp");
                            if(!rs.next()){
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
                                                                    
                                        switch (ch3) {
                                            case 1:
                                                rs = stmt.executeQuery("select * from emp order by eid");
                                                break;
                                            case 2:
                                                rs = stmt.executeQuery("select * from emp order by designation");
                                                break;
                                            case 3:
                                                rs = stmt.executeQuery("select * from emp order by name");
                                                break;
                                            case 4:
                                                rs = stmt.executeQuery("select * from emp order by age");
                                                break;
                                            case 5:
                                                rs = stmt.executeQuery("select * from emp order by salary");
                                                break;
                                        }

                                        while (rs.next()) {
                                            int id = rs.getInt("eid");
                                            String name = rs.getString("name");
                                            String designation = rs.getString("designation");
                                            int age = rs.getInt("age");
                                            double salary = rs.getDouble("salary");
                    
                                            System.out.println("Employee ID: " + id);
                                            System.out.println("Name: " + name);
                                            System.out.println("Designation: " + designation);
                                            System.out.println("Age: " + age);
                                            System.out.println("Salary: " + salary);
                                            System.out.println("---------------------------------------------");
                                        }
                                    } catch (CEOExistsException e) {
                                        System.out.println(e.getMessage());
                                    } 
                                } while (ch3 != 6);
                            }
                            rs.close(); 
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    break;

                case 3:
                    try {
                        System.out.println("Enter ID for appraisal: ");
                        int eid = Integer.parseInt(br.readLine());
                        System.out.println("Enter amount for appraisal: ");
                        float salary = Float.parseFloat(br.readLine());
                        DB.raiseEmp(eid,salary);
                    } catch (Exception e) {
                        System.out.println("Invalid ID input. Please enter a valid integer.");
                    }
                    break;
                
                case 4:
                    try {
                        System.out.println("Enter the ID to delete: ");
                        int eid = Integer.parseInt(br.readLine());
                        DB.deleteEmp(eid);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.println("Search By: ");
                    {
                        do {
                            System.out.println("---------------------------------------------");
                            System.out.println("1. ID");
                            System.out.println("2. Designation");
                            System.out.println("3. Name");
                            System.out.println("4. Back");
                            System.out.println("--------------------------------------------");
                            ch3 = Menu.readChoice(4);

                            switch (ch3) {
                                case 1:
                                    try {
                                        System.out.println("Enter ID: ");
                                        String eid = br.readLine();
                                        DB.searchEmp(1,eid);
                                    } catch (Exception e) {
                                        System.out.println("Invalid ID input. Please enter a valid integer.");
                                    }
                                    break;
                                case 2:
                                    try {
                                        System.out.println("Enter Designation: ");
                                        String designation = br.readLine();
                                        DB.searchEmp(2,designation);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    break;
                                case 3:
                                    try {
                                        System.out.println("Enter Name: ");
                                        String name = br.readLine();
                                        DB.searchEmp(3,name);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                break;
                            }
                        } while (ch3 != 4);
                            
                        }
                    break;
            }
        } while (ch1 != 6);

        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }
}
