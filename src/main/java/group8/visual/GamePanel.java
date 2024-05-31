package group8.visual;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import group8.domain.interactables.Controller;
import group8.domain.interactables.Game;
import group8.domain.objects.Barrier;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GamePanel {
    private App app; 
    private JPanel panel;
    private JLabel background;
    private transient Controller cont;
    private Game game;
    private JLabel[] lifeIcons;
    private ArrayList<ObjectVisual> objectVisuals;
    private ObjectVisual staff;
    private ObjectVisual fireball;


    public GamePanel(){
        initPanel();
    }

    private void initPanel(){
        panel = new JPanel();
        panel.setSize(1200, 680);
        panel.setLayout(null);
        background = new JLabel(new ImageIcon("src/main/java/group8/Graphical-Assets/200Background.png"));
        this.cont = new Controller();
        panel.addKeyListener(cont.getKeyListener());
    }

    public void setupGame(Game g){
        game = g;
        setupVisuals();
    }

    private void setupVisuals(){
        setupBarrierVisuals();
        setupPlayerVisuals();
        setupBackgroundVisuals();
    }

    private void setupBarrierVisuals(){
        ArrayList<Barrier> barriers = new ArrayList<>(game.getLevel().getBarriers());
        objectVisuals = new ArrayList<>();
        barriers.forEach(e -> objectVisuals.add((new ObjectVisual(e, e.getType()))));
        objectVisuals.forEach(e -> panel.add(e.getLabel()));
    }

    private void setupPlayerVisuals(){
        staff = new ObjectVisual(game.getStaff(), 4);
        panel.add(staff.getLabel());
        fireball = new ObjectVisual(game.getBall(), 5);
        panel.add(fireball.getLabel());
    }

    private void setupBackgroundVisuals(){
        //TODO: Life Icons added here (Before background)
        panel.add(background);
        background.setBounds(panel.getBounds());
    }

    public void refresh(){
        ArrayList<ObjectVisual> removeList = new ArrayList<>();
        for (ObjectVisual visual : objectVisuals){
            Barrier barrier = (Barrier) visual.getObject();
            if (barrier.isDestroyed()){
                game.getLevel().removeBarrier(barrier);
                removeList.add(visual);
            }
        }
        removeList.forEach(e -> panel.remove(e.getLabel()));

    }
}
