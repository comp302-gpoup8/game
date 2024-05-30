package group8.visual;

import javax.swing.JFrame;
import javax.swing.JPanel;

import group8.domain.interactables.Game;
import group8.domain.managers.Level;
import group8.domain.managers.Player;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class App {
    private JFrame mainFrame;
    private MainMenu mainMenu;
    private SinglePlayerMenu singlePlayerMenu;
    private BuildModeMenu buildModeMenu;
    private GamePanel gamePanel;
    private Game game;
    private Player player; 

    public App() {
        mainFrame = new JFrame("Game Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
        mainFrame.setResizable(false);

        mainMenu = new MainMenu(1200, 680);
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
        singlePlayerMenu = new SinglePlayerMenu(1200, 680);
        singlePlayerMenu.setApp(this); 
        displayMenu(singlePlayerMenu.getPanel());
    }

    public void showBuildModeMenu(){
        buildModeMenu = new BuildModeMenu(1200, 680);
        buildModeMenu.setApp(this);
        displayMenu(buildModeMenu.panel);
    }

    public void showGamePanel(){
        if (game == null) {
            game = new Game(new Level(2), player);
        }
        gamePanel = new GamePanel();
        gamePanel.setApp(this);
        gamePanel.setupGame(game);
        displayMenu(gamePanel.getPanel());
        mainFrame.setFocusable(true);
    }
}
