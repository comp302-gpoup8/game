package com.example.visual;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterPanel extends JPanel{
    public JButton loginButton;
    public JButton registerButton;
    public JTextField usernameField;
    public JTextField passwordField;
    public RegisterPanel(){
        setLayout(new GridLayout(4,1));

        usernameField = new JTextField();
        passwordField = new JTextField();
        loginButton = new JButton("login");
        registerButton = new JButton("register");

        registerButton.addActionListener(e -> registerClicked());

        add(usernameField);
        add(passwordField);
        add(registerButton);
        add(loginButton);
    }

    public void run() {
        JFrame frame = new JFrame("Register Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void registerClicked(){
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:projectdemo/demo/src/main/java/com/example/players.db");
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usernameField.getText());
            pstmt.setString(2, passwordField.getText());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
