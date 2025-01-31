//java -cp ".;C:\Users\Wissen\Desktop\postgresql-42.7.5.jar" JdbcDemo4
import java.sql.*;

public class JdbcDemo4{
    public static void main(String[] args) {
        try(
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            Statement stmt = con.createStatement();
        ) {
            stmt.addBatch("insert into emp values('Carol',19)");
            stmt.addBatch("insert into emp values('Carina',19)");
            stmt.addBatch("insert into emp values('Lisa',21)");
            stmt.addBatch("insert into emp values('Sania',22)");
            stmt.addBatch("insert into emp values('Sanika',23)");
            System.out.println("Wait for 20 secs to see the whole batch execution...");
            Thread.sleep(20000);

            stmt.executeBatch();
            System.out.println("You can find all the records in the table...");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

