package com.example.visual;

import com.example.database.AuthenticationFacade;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel{
    public JButton loginButton;
    public JButton registerButton;
    public JTextField usernameField;
    public JTextField passwordField;
    public JFrame registerFrame;
    public JDialog registerDialog;
    public JButton okButton;

    public RegisterPanel(){
        registerFrame = new JFrame("Registration Menu");
        setLayout(new GridLayout(4,1));

        registerDialog = new JDialog();

        usernameField = new JTextField();
        passwordField = new JTextField();
        loginButton = new JButton("login");
        registerButton = new JButton("register");

        registerButton.addActionListener(e -> registerClicked());
        loginButton.addActionListener(e -> openLogin());

        add(usernameField);
        add(passwordField);
        add(registerButton);
        add(loginButton);
    }

    public void openLogin(){
        LoginPanel openLoginWindow = new LoginPanel();
        openLoginWindow.run();
        registerFrame.dispose();
    }

    public void run() {
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.add(this);
        registerFrame.setSize(300, 200);
        registerFrame.setVisible(true);
        registerFrame.setLocationRelativeTo(null);
    }

    private void registerClicked(){
        AuthenticationFacade authenticationFacade = new AuthenticationFacade();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(authenticationFacade.isUsernameAvailable(username)){
            authenticationFacade.register(username, password);
//            registerDialog = new JDialog();
//            registerDialog.add(okButton);
//            registerDialog.add(new JLabel("Registration Successful!"));
        }
        else{
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.showErrorDialog();
        }

    }
}
