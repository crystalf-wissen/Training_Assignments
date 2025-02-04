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

            rs.addRowSetListener(new MyListener());
            rs.addRowSetListener(new MyListener2());

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

class MyListener implements RowSetListener
{
    public void cursorMoved(RowSetEvent evt)
    {
        System.out.println("------Cursor Moved--------");
    }
    public void rowChanged(RowSetEvent evt)
    {
        System.out.println("------Row Changed--------");
    }
    public void rowSetChanged(RowSetEvent evt)
    {
        System.out.println("------Row Set Changed--------");
    }
}

class MyListener2 implements RowSetListener
{
    public void cursorMoved(RowSetEvent evt)
    {
        System.out.println("------Cursor Moved--------");
    }
    public void rowChanged(RowSetEvent evt)
    {
        System.out.println("------Row Changed--------");
    }
    public void rowSetChanged(RowSetEvent evt)
    {
        System.out.println("------Row Set Changed--------");
    }
}