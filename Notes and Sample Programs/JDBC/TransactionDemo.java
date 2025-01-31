//java -cp ".;C:\Users\Wissen\Desktop\postgresql-42.7.5.jar" TransactionDemo

import java.io.*;
import java.sql.*;

public class TransactionDemo{
    public static void main(String[] args) {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into emp values(?,?)");
            ) {
            con.setAutoCommit(false);

            for(int i=1;i<=10;i++){
                System.out.println("Enter name: ");
                String name = br.readLine();
                System.out.println("Enter age: ");
                int age = Integer.parseInt(br.readLine());
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.execute();

                if(i==5)
                    con.rollback();
                if(i==10)
                    con.commit();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

