package com.example.database;

import java.sql.*;

class SQLSubsystem {
    private String username;
    private String password;
    private String sql;
    private String url = "jdbc:sqlite:projectdemo/demo/src/main/java/com/example/database/players.db";

    protected boolean registerSQL(String username, String password){
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    protected boolean loginSQL(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return password.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
