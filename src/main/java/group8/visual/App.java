package group8.visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import group8.domain.interactables.Game;
import group8.domain.managers.Level;
import group8.domain.managers.Player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    private JFrame mainFrame;
    private MainMenu mainMenu;
    private SinglePlayerMenu singlePlayerMenu;
    private BuildModeMenu buildModeMenu;
    private LoginMenu loginMenu;
    private GamePanel gamePanel;
    private Game game;
    private Player player; 
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

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

        gamePanel = new GamePanel();
        gamePanel.setApp(this);
        gamePanel.setupGame();
        displayMenu(gamePanel.getPanel());

        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();

        executorService.submit(gamePanel);
        executorService.submit(game);  
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * @return the mainFrame
     */
    public JFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * @param mainFrame the mainFrame to set
     */
    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * @return the mainMenu
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * @param mainMenu the mainMenu to set
     */
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * @return the singlePlayerMenu
     */
    public SinglePlayerMenu getSinglePlayerMenu() {
        return singlePlayerMenu;
    }

    /**
     * @param singlePlayerMenu the singlePlayerMenu to set
     */
    public void setSinglePlayerMenu(SinglePlayerMenu singlePlayerMenu) {
        this.singlePlayerMenu = singlePlayerMenu;
    }

    /**
     * @return the buildModeMenu
     */
    public BuildModeMenu getBuildModeMenu() {
        return buildModeMenu;
    }

    /**
     * @param buildModeMenu the buildModeMenu to set
     */
    public void setBuildModeMenu(BuildModeMenu buildModeMenu) {
        this.buildModeMenu = buildModeMenu;
    }

    /**
     * @return the loginMenu
     */
    public LoginMenu getLoginMenu() {
        return loginMenu;
    }

    /**
     * @param loginMenu the loginMenu to set
     */
    public void setLoginMenu(LoginMenu loginMenu) {
        this.loginMenu = loginMenu;
    }

    /**
     * @return the gamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * @param gamePanel the gamePanel to set
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the executorService
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
