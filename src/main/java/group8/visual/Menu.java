package group8.visual;

import java.awt.Dimension;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Menu implements MenuInterface {
    
    protected JPanel panel;

    public Menu (int width, int height){
        this(new Dimension(width, height));
    }

    public Menu(Dimension bounds){
        panel = new JPanel();
        panel.setSize(bounds);
    }
}
