package group8.visual;

import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import javax.swing.*;

@Getter @Setter
public class SinglePlayerMenu extends Menu {
    private JPanel panel;
    private JButton quickPlayButton;
    private JButton buildModeButton;
    private JButton loadGameButton;

    public SinglePlayerMenu(int width, int height) {
        super(width, height);
        buildPanel();
    }

    public void buildPanel() {
        panel = new JPanel();
        panel.setSize(size);
        panel.setName("Singleplayer Menu");
        panel.setLayout(new GridLayout(3, 1));
        setupComponents();

        panel.add(quickPlayButton);
        panel.add(buildModeButton);
        panel.add(loadGameButton);
    }

    public void setupComponents(){
        quickPlayButton = new JButton("Quick Play");
        buildModeButton = new JButton("Build Mode");
        loadGameButton = new JButton("Load Game");

        quickPlayButton.addActionListener(e->System.out.println("Todo"));
        buildModeButton.addActionListener(e->System.out.println("Todo"));
        loadGameButton.addActionListener(e->System.out.println("todo"));
    }

    @Override
    public void publish() {
        throw new UnsupportedOperationException("Unimplemented method 'publish'");
    }
}