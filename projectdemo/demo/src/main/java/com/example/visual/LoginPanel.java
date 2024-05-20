package com.example.visual;

import com.example.database.AuthenticationFacade;

import javax.swing.*;
import java.awt.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    public JButton loginButton;
    public JButton registerButton;
    public JTextField usernameField;
    public JTextField passwordField;
    public boolean LoginSuccessful;
    public JFrame loginFrame;

    public LoginPanel(){
        loginFrame = new JFrame("Login Menu");
        LoginSuccessful = false;
        setLayout(new GridLayout(4,1));

        usernameField = new JTextField();
        passwordField = new JTextField();
        loginButton = new JButton("login");
        registerButton = new JButton("register");

        registerButton.addActionListener(e -> openRegistration());
        loginButton.addActionListener(e -> loginClicked());

        add(usernameField);
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }


    public void openRegistration(){
        RegisterPanel openRegistrationWindow = new RegisterPanel();
        openRegistrationWindow.run();
        loginFrame.dispose();
    }

    public void loginClicked(){
        AuthenticationFacade authenticationFacade = new AuthenticationFacade();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(authenticationFacade.login(username, password)){
            LoginSuccessful = true;
            MainMenuPanel mainMenu = new MainMenuPanel();
//            while (mainMenu.getGame() == null){
//                mainMenu.run();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//            Game game = mainMenu.getGame();
//            mainMenu = null;
//            game.run();
            mainMenu.run();
        }
    }

    public void run() {
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.add(this);
        loginFrame.setSize(300, 200);
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
    }




}
