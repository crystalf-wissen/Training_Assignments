import java.sql.*;

public class ResultSetDemo {
    public static void main(String args[]) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
			//Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);				...by default this is available
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery("select * from emp");

            while (rs.next()) {
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(1));
                System.out.println();
            }

            System.out.println("------------------");

            while (rs.previous()) {
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(1));
                System.out.println();
            }

            System.out.println("------------------");

            rs.absolute(3); 
			System.out.println("Name: " + rs.getString(2));
			System.out.println("Age: " + rs.getInt(1));
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
