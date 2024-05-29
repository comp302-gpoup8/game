package group8.visual;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    private JFrame mainFrame;
    private MainMenu mainMenu;
    private SinglePlayerMenu singlePlayerMenu;
    private BuildModeMenu buildModeMenu;

    public App() {
        mainFrame = new JFrame("Game Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        mainMenu = new MainMenu(800, 600);
        mainMenu.setApp(this); 
        displayMenu(mainMenu.getPanel());
    }

    public void displayMenu(JPanel menuPanel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(menuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public void showSinglePlayerMenu() {
        singlePlayerMenu = new SinglePlayerMenu(800, 600);
        singlePlayerMenu.setApp(this); 
        displayMenu(singlePlayerMenu.getPanel());
    }

    public void showBuildModeMenu(){
        buildModeMenu = new BuildModeMenu(800, 600);
        buildModeMenu.setApp(this);
        displayMenu(buildModeMenu.panel);
    }
}
