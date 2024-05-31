package group8.visual;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import group8.database.Authenticator;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginMenu extends Menu {
    
    private JButton loginButton;
    private JButton registerButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private JDialog registerDialog;
    private App app;
    private boolean authenticated;
    
    public LoginMenu(int width, int height){
        super(width,height);
        buildPanel();
        setupComponents();
    }


    private void setupButtons() {
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        registerButton.addActionListener(e -> registerClicked());
        loginButton.addActionListener(e -> loginClicked());
    }

    private void setupTextFields() {
        usernameField = new JTextField();
        passwordField = new JTextField();
    }

    private void addComponents() {
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
    }

    private void registerClicked() {
        Authenticator auth = new Authenticator();
        auth.register(usernameField.getText(), passwordField.getText());
    }

    private void loginClicked() {
        Authenticator auth = new Authenticator();
        authenticated = auth.login(usernameField.getText(), passwordField.getText());
        if (authenticated){
            app.showMainMenu();
        }
    }

    @SuppressWarnings("unused")
    private boolean validateCredentials(String username, String password, Authenticator auth) {
        if (username.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Required fields cannot be left blank!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        } else if (auth.usernameTaken(username)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Username already taken. Please try a different one.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Registration successful",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        }
    }

    @Override
    public void buildPanel() {
        this.panel = new JPanel();
        panel.setSize(size);
        panel.setName("Login Menu");
        panel.setLayout(new GridLayout(4, 1));
        setupComponents();
        addComponents();
        
    }

    @Override
    public void setupComponents() {
        setupButtons();
        setupTextFields();
    }

    @Override
    public void publish() {
        app.displayMenu(panel);
    }
    
}
