package com.example.visual;

import com.example.domain.levels.Level;
import com.example.domain.levels.LevelBuilder;
import com.example.domain.Game;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(login(username, password)){
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


    public boolean login(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:projectdemo/demo/src/main/java/com/example/players.db");
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
