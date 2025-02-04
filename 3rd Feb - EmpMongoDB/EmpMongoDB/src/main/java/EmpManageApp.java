import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

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
    	MongoCollection<Document> collection = DatabaseUtil.getConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter the EID: ");
            int eid = 0;
            try {
                eid = Integer.parseInt(br.readLine());
                try {
                	Bson filter = Filters.eq("eid", eid);
                	Document existingEmp = collection.find(filter).first();
                	
                    if (existingEmp != null) {
                        throw new InvalidEIDException();
                    }
                    return eid;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a valid number.");
            }
             catch (Exception e) {
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

interface EmpDAO
{
    void createNew(Emp emp);
    void searchEmp(int option,String param);
    void deleteEmp(int eid);
    void raiseEmp(int eid, float raiseAmount);
    void displayAll(int choice);
}

 class DatabaseUtil {

    private static MongoClient mongoClient;

    private DatabaseUtil() {
    }

    public static MongoCollection<Document> getConnection() {
        try {
        	mongoClient = MongoClients.create("mongodb://localhost:27017");
        	MongoDatabase database = mongoClient.getDatabase("demodb");
        	MongoCollection<Document> collection = database.getCollection("Employee");
        	return collection;
        } catch (Exception e) {
            System.out.println("Error getting the connection " + e.getMessage());
            return null;
        }
    }

     public static void closeConnection() {
        try {
        	mongoClient.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        	mongoClient = null;
        }
    }

}

class EmpDAOImpl implements EmpDAO{

    public EmpDAOImpl() {
    }

    public void createNew(Emp newEmp) {
        try {
        	MongoCollection<Document> collection =  DatabaseUtil.getConnection();
 
    		Document doc = new Document();
    		doc.append("eid", newEmp.eid);
    		doc.append("name", newEmp.name);
    		doc.append("age", newEmp.age);
    		doc.append("salary", newEmp.salary);
    		doc.append("designation", newEmp.designation);
    		collection.insertOne(doc);
 
            System.out.println("Employee Saved!");
 
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void searchEmp(int option, String param) {
        try {
        	MongoCollection<Document> collection =  DatabaseUtil.getConnection();
            Bson filter = null;
            FindIterable<Document> i = null;

            switch (option) {
                case 1:
                	filter = Filters.eq("eid",Integer.parseInt(param));
                    break;

                case 2:
                	filter = Filters.eq("designation",param);
                    break;

                case 3:
                	filter = Filters.eq("name",param);
                    break;
            }
            i = collection.find(filter);
            displayResults(i);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void deleteEmp(int eid) {
        try {
        	MongoCollection<Document> collection =  DatabaseUtil.getConnection();
        	
    		Bson filter = Filters.eq("eid",eid);
    		DeleteResult result = collection.deleteOne(filter);
    		
            if (result.getDeletedCount() > 0) {
                System.out.println("Employee with ID "+eid+" has been successfully deleted.");
            } else {
                System.out.println("Could not delete");
            }
    		
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void raiseEmp(int eid, float raiseAmount) {
        try {
            MongoCollection<Document> collection = DatabaseUtil.getConnection();
            Bson filter = Filters.eq("eid", eid);
            Document employee = collection.find(filter).first();
            if (employee != null) {
                Bson update = Updates.inc("salary", raiseAmount);
                collection.updateOne(filter, update);
                System.out.println("Employee salary updated");
            } else {
                System.out.println("Employee with ID " +eid + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void displayAll(int choice){
        try {
        	MongoCollection<Document> collection =  DatabaseUtil.getConnection();
        	Bson sort = null;
            switch (choice) {
                case 1:
                    sort = Sorts.ascending("eid");
                    break;
                case 2:
                	sort = Sorts.ascending("designation");
                    break;
                case 3:
                	sort = Sorts.ascending("name");
                    break;
                case 4:
                	sort = Sorts.ascending("age");
                    break;
                 case 5:
                	 sort = Sorts.ascending("salary");
                    break;
                 case 6:
                	 return;
             }
    		FindIterable<Document> i = collection.find().sort(sort);
            displayResults(i);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayResults(FindIterable<Document> i) {
    	Bson projection = Projections.exclude("_id");
    	i.projection(projection);
    	
		for(Document d : i) {
			System.out.println(d.toJson());
		}
    }
}


public class EmpManageApp {
    public static void main(String[] args) {
    	
        EmpDAO empDAO = new EmpDAOImpl();

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
                            if(newEmp!=null){
                                empDAO.createNew(newEmp);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (ch2 != 5);
                    break;

                case 2:

                        if(checkEmptyTable()){
                            System.out.println("No Employee Present to Display");
                        } else {
                             do {
                                    System.out.println("Display By: ");
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
                                        empDAO.displayAll(ch3);
                                    } catch (CEOExistsException e) {
                                        System.out.println(e.getMessage());
                                    }
                             } while (ch3 != 6);
                        }
                    break;

                case 3:
                    try {
                        System.out.println("Enter ID for appraisal: ");
                        int eid = Integer.parseInt(br.readLine());
                        System.out.println("Enter amount for appraisal: ");
                        float salary = Float.parseFloat(br.readLine());
                        empDAO.raiseEmp(eid,salary);
                    } catch (Exception e) {
                        System.out.println("Invalid ID input. Please enter a valid integer.");
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter the ID to delete: ");
                        int eid = Integer.parseInt(br.readLine());
                        empDAO.deleteEmp(eid);
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
                                        empDAO.searchEmp(1,eid);
                                    } catch (Exception e) {
                                        System.out.println("Invalid ID input. Please enter a valid integer.");
                                    }
                                    break;
                                case 2:
                                    try {
                                        System.out.println("Enter Designation: ");
                                        String designation = br.readLine();
                                        empDAO.searchEmp(2,designation);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    break;
                                case 3:
                                    try {
                                        System.out.println("Enter Name: ");
                                        String name = br.readLine();
                                        empDAO.searchEmp(3,name);
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
        DatabaseUtil.closeConnection();
    }

    private static boolean checkEmptyTable() {
        try {
            MongoCollection<Document> collection = DatabaseUtil.getConnection();
            long count = collection.countDocuments();
            return count == 0;
            
        } catch (Exception e) {
            System.out.println(e);
             return true;
        }
    }
}