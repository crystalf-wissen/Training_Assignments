package com.example.demo.service;

import org.springframework.stereotype.Service;

import demo.example.demo.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {

    public boolean validateUserLogin(int id, String password) {
        String query = "SELECT * FROM users WHERE id = ? AND password = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false; 
    }
    
    public boolean UserSignup(int id, String name, String password) {
        String query = "SELECT * FROM users WHERE id = ?";
        String insertQuery = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";


        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return false;
                } else {
                	 try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                         insertStmt.setInt(1, id);
                         insertStmt.setString(2, name);
                         insertStmt.setString(3, password);
                         insertStmt.executeUpdate(); 
                         return true;
                     }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
		return false;
    }
    
    public String getUserNameById(int id) {
        String query = "SELECT name FROM users WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id); 
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
        return null; 
    }

}
