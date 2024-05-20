package com.example.visual;
import com.example.database.AuthenticationFacade;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog {
    private String message;
    public ErrorDialog() {
        AuthenticationFacade authenticationFacade = new AuthenticationFacade();
        message = authenticationFacade.getMessage();
    }

    public void showErrorDialog() {
        JOptionPane.showMessageDialog(new JFrame(), this.message, "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }
}
