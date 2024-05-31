package group8.visual;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import group8.domain.interactables.Controller;
import group8.domain.objects.Barrier;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GamePanel implements Runnable {
    private App app; 
    private JPanel panel;
    private JLabel[] lifeIcons;
    private ArrayList<ObjectVisual> objectVisuals;
    private ObjectVisual staff;
    private ObjectVisual fireball;
    private transient Controller cont;


    public GamePanel(){
        initPanel();
    }

    private void initPanel(){
        panel = new JPanel();
        panel.setSize(1200, 680);
        panel.setLayout(null);
        this.cont = new Controller();
        panel.addKeyListener(cont.getKeyListener());
    }

    public void setupGame(){
        setupVisuals();
    }

    private void setupVisuals(){
        setupBarrierVisuals();
        setupPlayerVisuals();
        setupBackgroundVisuals();
    }

    private void setupBarrierVisuals(){
        ArrayList<Barrier> barriers = new ArrayList<>(app.getGame().getLevel().getBarriers());
        objectVisuals = new ArrayList<>();
        barriers.forEach(e -> objectVisuals.add((new ObjectVisual(e, e.getType()))));
        objectVisuals.forEach(e -> panel.add(e.getLabel()));
    }

    private void setupPlayerVisuals(){
        staff = new ObjectVisual(app.getGame().getManager().getStaff(), 4);
        panel.add(staff.getLabel());
        fireball = new ObjectVisual(app.getGame().getManager().getBall(), 5);
        panel.add(fireball.getLabel());
    }

    private void setupBackgroundVisuals(){
        JLabel background = new JLabel(new ImageIcon("src/main/java/group8/Graphical-Assets/200Background.png"));
        panel.add(background);
        background.setBounds(panel.getBounds());
    }

    @Override
    public void run() {
        final int FPS = 30;
        final long frameTime = 1000 / FPS;

        while (true){
            long startTime = System.currentTimeMillis();
            app.getGame().updateGameState();
            refreshVisuals();
            panel.repaint();

            long endTime = System.currentTimeMillis();
            long deltaTime = endTime - startTime;

            long sleepTime = frameTime - deltaTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void refreshVisuals() {
        // Update the position of the staff
        staff.updatePosition(app.getGame().getManager().getStaff().getLocation());

        // Update the position of the fireball
        fireball.updatePosition(app.getGame().getManager().getBall().getLocation());

        // Update the positions of the barriers
        for (ObjectVisual visual : objectVisuals) {
            visual.updatePosition(visual.getObject().getLocation());
        }
    }
}
