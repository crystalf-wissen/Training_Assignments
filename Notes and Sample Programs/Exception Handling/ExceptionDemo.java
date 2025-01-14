import java.io.*;
import java.sql.*;

class A {
    public void abc() throws IOException, SQLException, UserException {
        int a = 5;
            for (int i = 1; i <= 20; i++) {
                System.out.println(i);

                int res = a / (a - i);

                if (i == 150)
                    throw new NullPointerException();

                if (i == 120)
                    throw new IOException();

                if (i == 100)
                    throw new SQLException();
                if (i == 60)
                    System.exit(0);
                if (i == 40)
                    throw new UserException("when i is 4");
                if (i == 200)
                    throw new UserException("when i is 2");
            }
        }

    public void xyz() throws IOException, SQLException, UserException, NullPointerException {
        try {
            abc();
        } catch (NullPointerException e) {
            System.out.println("Handling null in xyz() method only");
        }
    }

    public void atoz() throws IOException, SQLException, UserException {
        xyz();
    }
}

public class ExceptionDemo {
    public static void main(String args[]) {
        try {
            A a1 = new A();
            a1.atoz();
        } catch (IOException | SQLException e) {
            System.out.println("IO/SQL Exception handler");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException handler");
        } catch (UserException e) {
            System.out.println("Custom exception handler...");
            System.out.println("Reason: " + e.getMessage());
            e.printStackTrace();
            e.display();
        } catch (Exception e) {
            System.out.println("Default Exception handler...");
        } finally {
            System.out.println("THANK YOU");
        }

        System.out.println("Program continues....");
    }
}

class UserException extends Exception {  // or extends RuntimeException
    public UserException() {
    }

    public UserException(String msg) {
        super(msg);
    }

    public void display() {
        // Display method implementation, you can leave it empty or add functionality.
        System.out.println("Displaying user exception details...");
    }
}
