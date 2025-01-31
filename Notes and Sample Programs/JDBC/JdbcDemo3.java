//java -cp ".;C:\Users\Wissen\Desktop\postgresql-42.7.5.jar" JdbcDemo3

import java.sql.*;
public class JdbcDemo3{
    public static void main(String[] args) {
        try(
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            CallableStatement cstmt = con.prepareCall("CALL dummy_record()");

        ) {
            cstmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

