package group8.visual;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainMenu extends Menu {
    private JPanel panel;
    private JButton singlePlayerButton;
    private JButton multiplayerButton;
    private App app;

    public MainMenu(int width, int height) {
        super(width, height);
        buildPanel();
    }

    @Override
    public void buildPanel() {
        this.panel = new JPanel();
        panel.setSize(size);
        panel.setName("Main Menu");

        panel.setLayout(new GridLayout(4, 1));

        setupComponents();
        panel.add(singlePlayerButton);
        panel.add(multiplayerButton);
    }

    @Override
    public void publish() {
        app.displayMenu(panel);
    }

    @Override
    public void setupComponents() {
        singlePlayerButton = new JButton("Single Player");
        singlePlayerButton.addActionListener(e -> app.showSinglePlayerMenu());

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.addActionListener(e -> System.out.println("TODO"));
    }

}