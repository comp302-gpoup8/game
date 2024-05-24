package com.example.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class SQLSubsystemTest {

    private SQLSubsystem sqlSubsystem;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        // Setup the in-memory SQLite database for testing
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        sqlSubsystem = new SQLSubsystem();

        // Create the users table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, username TEXT UNIQUE NOT NULL, password TEXT NOT NULL)");
        }
    }

    /**
     * REQUIRES: username and password are valid and unique
     * MODIFIES: this.database
     * EFFECTS: Registers a new user with the given username and password
     */
    @Test
    public void testRegisterUserValid() throws SQLException {
        boolean result = sqlSubsystem.registerSQL("testuser1", "Password123!");
        assertTrue(result);
    }

    /**
     * REQUIRES: username already exists
     * MODIFIES: this.database
     * EFFECTS: Fails to register a new user due to duplicate username
     */
    @Test
    public void testRegisterUserDuplicateUsername() throws SQLException {
        sqlSubsystem.registerSQL("testuser2", "Password123!");
        boolean result = sqlSubsystem.registerSQL("testuser2", "Password123!");
        assertFalse(result);
    }

    /**
     * REQUIRES: username and password are correct
     * MODIFIES: none
     * EFFECTS: Authenticates the user and returns true
     */
    @Test
    public void testAuthenticateUserValid() throws SQLException {
        sqlSubsystem.registerSQL("testuser3", "Password123!");
        boolean result = sqlSubsystem.loginSQL("testuser3", "Password123!");
        assertTrue(result);
    }

    /**
     * REQUIRES: username is correct but password is incorrect
     * MODIFIES: none
     * EFFECTS: Fails to authenticate the user and returns false
     */
    @Test
    public void testAuthenticateUserInvalidPassword() throws SQLException {
        sqlSubsystem.registerSQL("testuser4", "Password123!");
        boolean result = sqlSubsystem.loginSQL("testuser4", "WrongPassword");
        assertFalse(result);
    }

    /**
     * REQUIRES: username does not exist
     * MODIFIES: none
     * EFFECTS: Fails to authenticate the user and returns false
     */
    @Test
    public void testAuthenticateUserNonExistentUsername() throws SQLException {
        boolean result = sqlSubsystem.loginSQL("nonexistentuser", "Password123!");
        assertFalse(result);
    }

    /**
     * REQUIRES: username and password are valid and unique
     * MODIFIES: this.database
     * EFFECTS: Ensures that the user is added to the database correctly
     */
    @Test
    public void testRegisterUserDatabaseState() throws SQLException {
        sqlSubsystem.registerSQL("testuser5", "Password123!");

        try (Statement stmt = connection.createStatement()) {
            var rs = stmt.executeQuery("SELECT * FROM users WHERE username = 'testuser5'");
            assertTrue(rs.next());
            assertEquals("testuser5", rs.getString("username"));
            assertEquals("Password123!", rs.getString("password"));
        }
    }
}
