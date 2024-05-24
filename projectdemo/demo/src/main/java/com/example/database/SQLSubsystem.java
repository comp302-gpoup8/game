package com.example.database;

import java.sql.*;

class SQLSubsystem {
    private String username;
    private String password;

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:projectdemo/demo/src/main/java/com/example/database/players.db");
    }

    protected boolean registerSQL(String username, String password){
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    protected boolean loginSQL(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection connection = getConnection();
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

    public boolean isUsernameTaken(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // returns true if a result is found
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
