package group8.database;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBMS (Database Management System) Class
 * Handles the login and register operations, and related sub-operations for the Authenticator.
 * @author Maksat Sanim - Orginial Class, Edits for Integration
 * @author Bedran Yilmaz Bakay - Edits for Integration, Javadocs
 */
@Getter @Setter
public class DBMS {



    /**
     * Returns a connection to the database.
     *
     * @return a connection to the SQLite database
     * @throws SQLException if a database access error occurs
     */
    private static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:src/main/java/group8/database/players.db");
    }


    /**
     * Registers a new user with the given username and password pair.
     * 
     * @param username the username of the user to register. Must be unique
     * @param password the password of the user to register
     * @return true if the user is successfully registered, false otherwise
     */
    protected boolean register (String username, String password){
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users(username, password) VALUES(?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Authenticates a user by checking if the provided username and password match the records in the database.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the username - password pair is found, false otherwise.
     */
    protected boolean login(String username, String password){
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return password.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Checks if a username is already taken by another user.
     *
     * @param username the username
     * @return true if the username is already taken, false otherwise
     */
    protected boolean usernameTaken(String username) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
            statement.setString(1, username);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
