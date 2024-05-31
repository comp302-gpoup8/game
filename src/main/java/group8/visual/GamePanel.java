package group8.visual;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import group8.domain.interactables.Controller;
import group8.domain.objects.Barrier;

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
        getApp().getGame().setPanel(this);
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

    //GamePanel
    @Override
    public void run() {
        while (Thread.currentThread().isInterrupted()){
            panel.repaint();
        } try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
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
    
    public int getDirectionSignal() {
        return cont.getSignal();
    }

    /**
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * @return the lifeIcons
     */
    public JLabel[] getLifeIcons() {
        return lifeIcons;
    }

    /**
     * @param lifeIcons the lifeIcons to set
     */
    public void setLifeIcons(JLabel[] lifeIcons) {
        this.lifeIcons = lifeIcons;
    }

    /**
     * @return the objectVisuals
     */
    public ArrayList<ObjectVisual> getObjectVisuals() {
        return objectVisuals;
    }

    /**
     * @param objectVisuals the objectVisuals to set
     */
    public void setObjectVisuals(ArrayList<ObjectVisual> objectVisuals) {
        this.objectVisuals = objectVisuals;
    }

    /**
     * @return the staff
     */
    public ObjectVisual getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(ObjectVisual staff) {
        this.staff = staff;
    }

    /**
     * @return the fireball
     */
    public ObjectVisual getFireball() {
        return fireball;
    }

    /**
     * @param fireball the fireball to set
     */
    public void setFireball(ObjectVisual fireball) {
        this.fireball = fireball;
    }

    /**
     * @return the cont
     */
    public Controller getCont() {
        return cont;
    }

    /**
     * @param cont the cont to set
     */
    public void setCont(Controller cont) {
        this.cont = cont;
    }

    
}
