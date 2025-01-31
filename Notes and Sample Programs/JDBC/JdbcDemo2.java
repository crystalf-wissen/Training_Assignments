//java -cp ".;C:\Users\Wissen\Desktop\postgresql-42.7.5.jar" JdbcDemo2

import java.io.*;
import java.sql.*;

public class JdbcDemo2{
    public static void main(String[] args) {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            Statement stmt = con.createStatement();
            ) {
            PreparedStatement pstmt = con.prepareStatement("insert into emp values(?,?)");
            System.out.println("Enter name: ");
            String name = br.readLine();
            System.out.println("Enter age: ");
            int age = Integer.parseInt(br.readLine());

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.execute();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

