package group8.visual;

import java.awt.Dimension;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Menu implements MenuInterface {
    
    public JPanel panel;
    public Dimension size;
    public App app;

    public Menu (int width, int height){
        this.size = new Dimension(width, height);
    }
}
