//java -cp ".;C:\Users\Wissen\Desktop\postgresql-42.7.5.jar" JdbcDemo
import java.sql.*;

public class JdbcDemo{
    public static void main(String[] args) {
        try(
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            Statement stmt = con.createStatement();
        ) {
            //Class.forName("org.postgresql.Driver");
            //stmt.executeUpdate("insert into emp values('Raju',25)");
            //stmt.executeUpdate("update emp set age=age+1");
            //stmt.executeUpdate("delete from emp where age<30");
            ResultSet rs = stmt.executeQuery("select * from emp");
            while(rs.next()){
                System.out.println("Name: "+rs.getString(1));
                System.out.println("Age: "+rs.getInt(2));
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

