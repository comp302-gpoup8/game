package com.example.database;

public class AuthenticationFacade {
    private SQLSubsystem SQLSubsystem;


    public AuthenticationFacade() {
        SQLSubsystem = new SQLSubsystem();
    }

    public boolean login(String username, String password) {
        return SQLSubsystem.loginSQL(username, password);
    }

    public boolean register(String username, String password) {
        return SQLSubsystem.registerSQL(username, password);
    }

    public boolean isUsernameAvailable(String username) {return SQLSubsystem.isUsernameTaken(username);}

    public String getMessage() {return SQLSubsystem.getErrorMessage();}

}
