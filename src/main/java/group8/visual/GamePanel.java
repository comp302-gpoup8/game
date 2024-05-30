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
    private ObjectVisual[] objectVisuals;
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
    }

    public void setupGame(Game g){
        game = g;
        setupVisuals();
    }

    private void setupVisuals(){
        ArrayList<Barrier> barriers = new ArrayList<>(game.getLevel().getBarriers());
        objectVisuals = new ObjectVisual[barriers.size()];
        for (int i = 0; i < barriers.size(); i++){
            ObjectVisual v = new ObjectVisual(barriers.get(i), barriers.get(i).getType());
            objectVisuals[i] = v;
            panel.add(v.getLabel());
        }

        staff = new ObjectVisual(game.getStaff(), 4);
        panel.add(staff.getLabel());
        fireball = new ObjectVisual(game.getBall(), 5);
        panel.add(fireball.getLabel());
        panel.add(background);
        background.setBounds(0, 0, 1200, 680);
    }
}
