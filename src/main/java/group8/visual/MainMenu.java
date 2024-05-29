package group8.visual;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainMenu extends Menu {
    private JPanel panel;
    private JButton singlePlayerButton;
    private JButton multiplayerButton;

    public MainMenu(int width, int height) {
        super(width, height);
        buildPanel();
    }

    @Override
    public void buildPanel() {
        panel.setLayout(new GridLayout(4, 1));

        setupComponents();
        panel.add(singlePlayerButton);
        panel.add(multiplayerButton);
    }

    @Override
    public void publish() {
        
    }

    @Override
    public void setupComponents() {
        singlePlayerButton = new JButton("Single Player");
        singlePlayerButton.addActionListener(e -> System.out.println("TODO"));

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.addActionListener(e -> System.out.println("TODO"));
    }

    
}