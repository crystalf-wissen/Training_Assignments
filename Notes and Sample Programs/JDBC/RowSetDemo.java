import javax.sql.*;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class RowSetDemo {
    public static void main(String[] args) {
        try {
            JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");

            rs.setCommand("select * from emp");

            rs.execute();

            while(rs.next())
            {
                System.out.println("EID: "+rs.getInt(1));
                System.out.println("Name: "+rs.getString(2));
                System.out.println("Age: "+rs.getInt(3));
                System.out.println("Salary: "+rs.getFloat(4));
                System.out.println("Designation: "+rs.getString(5));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }     
}