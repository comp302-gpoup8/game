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

}
