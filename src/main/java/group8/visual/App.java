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
    private LoginMenu loginMenu;
    private GamePanel gamePanel;
    private Game game;
    private Player player; 

    public App() {
        buildMainFrame();
        showLoginMenu();
    }

    public void buildMainFrame(){
        mainFrame = new JFrame("Game Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 680);
        mainFrame.setResizable(false);
    }

    public void displayMenu(JPanel menuPanel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(menuPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    public void showLoginMenu(){
        loginMenu = new LoginMenu(1200, 680);
        loginMenu.setApp(this);
        displayMenu(loginMenu.getPanel());
    }
    public void showMainMenu(){
        mainMenu = new MainMenu(1200, 680);
        mainMenu.setApp(this);
        displayMenu(mainMenu.getPanel());
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
        /* If there is not a game, quickplay creates a new game with normal difficulty for the current player. */
        if (game == null) {
            game = new Game(new Level(2));
            game.setApp(this);
        }

        /* Panel for the game is initialized */
        gamePanel = new GamePanel();
        gamePanel.setApp(this);

        /* Game panel is filled with the content of the level and displayed. */
        gamePanel.setupGame();
        displayMenu(gamePanel.getPanel());


        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();

        // Start the game loop in a new thread
        new Thread(gamePanel).start();
        new Thread(game).start();    
    }
}
