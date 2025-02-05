package sqlJSON;

import java.sql.*;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class DatabaseConnection {
    private static Connection con;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "tiger";

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection created.");
            }
            return con;
        } catch (Exception e) {
            System.out.println("Error getting the connection ");
            return null;
        }
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con = null;
        }
    }
}

class CRUD {

	public static void insertUser(int id, String name, JSONObject info) {
	    String checkSQL = "SELECT 1 FROM people WHERE data->>'id' = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement checkStmt = conn.prepareStatement(checkSQL)) {

	        checkStmt.setString(1, String.valueOf(id)); 
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("User with ID " + id + " already exists. Data will not be inserted.");
	            return;
	        }

	        System.out.print("Enter name: ");
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        name = br.readLine();

	        System.out.print("Enter email: ");
	        String email = br.readLine();

	        System.out.print("Enter age: ");
	        int age = Integer.parseInt(br.readLine());

	        System.out.print("Enter active status (true/false): ");
	        boolean active = Boolean.parseBoolean(br.readLine());

	        JSONObject jsonData = new JSONObject();
	        jsonData.put("id", id);
	        jsonData.put("name", name);
	        jsonData.put("info", info);
	        jsonData.getJSONObject("info").put("email", email);
	        jsonData.getJSONObject("info").put("age", age);
	        jsonData.getJSONObject("info").put("active", active);

	        String insertSQL = "INSERT INTO people (data) VALUES (?::jsonb)";
	        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
	            insertStmt.setString(1, jsonData.toString());
	            insertStmt.executeUpdate();
	            System.out.println("Data inserted successfully with User ID: " + id);
	        } catch (SQLException e) {
	            System.out.println("Error inserting data: " + e.getMessage());
	        }

	    } catch (SQLException | IOException e) {
	        System.out.println("Error checking for existing user or reading input: " + e.getMessage());
	    }
	}


    public static void readUser(long id) {
        String sql = "select data from people where data->>'id' = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(id));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String jsonData = rs.getString("data");
                JSONObject jsonObject = new JSONObject(jsonData);
                System.out.println("User Data: " + jsonObject.toString(4));
            } else {
                System.out.println("No user found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateUser(long id, boolean newActive) {
        String sql = "update people set data = jsonb_set(data, '{info, active}', to_jsonb(?::boolean)) where data->>'id' = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, newActive);
            pstmt.setString(2, String.valueOf(id));  
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User's active status updated to " + newActive);
            } else {
                System.out.println("No user found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteUser(long id) {
        String sql = "delete from people where data->>'id' = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(id));
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted");
            } else {
                System.out.println("No user found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

public class PeopleSQL {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n--- CRUD Operations ---");
            System.out.println("1. Insert User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(br.readLine());
                    CRUD.insertUser(userId, null, new JSONObject());
                    break;

                case 2:
                    System.out.print("Enter user ID to read: ");
                    long readId = Long.parseLong(br.readLine());
                    CRUD.readUser(readId);
                    break;

                case 3:
                    System.out.print("Enter user ID to update active status: ");
                    long updateId = Long.parseLong(br.readLine());
                    System.out.print("Enter new active status (true/false): ");
                    String newActiveInput = br.readLine();
                    boolean newActive = Boolean.parseBoolean(newActiveInput);
                    CRUD.updateUser(updateId, newActive);
                    break;

                case 4:
                    System.out.print("Enter user ID to delete: ");
                    long deleteId = Long.parseLong(br.readLine());
                    CRUD.deleteUser(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    DatabaseConnection.closeConnection();
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
