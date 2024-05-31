package group8.visual;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jetbrains.annotations.NotNull;

import group8.domain.objects.GameObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ObjectVisual {
    
    private JLabel label;
    private GameObject object;
    private ImageIcon icon;


    public ObjectVisual(@NotNull GameObject object){
        this(object, 0);
    }

    public ObjectVisual(@NotNull GameObject object, @NotNull int type){
        this.object = object;
        icon = new ImageIcon(getImagePath(type));
        initLabel();
    }

    private String getImagePath(int type){
        return switch (type) {
            default -> "src/main/java/group8/Graphical-Assets/iconbluegem.png";
            case 1 -> "src/main/java/group8/Graphical-Assets/iconredgem.png";
            case 2 -> "src/main/java/group8/Graphical-Assets/icongreengem.png";
            case 3 -> "src/main/java/group8/Graphical-Assets/firm.png";
            case 4 -> "src/main/java/group8/Graphical-Assets/Player.png";
            case 5 -> "src/main/java/group8/Graphical-Assets/Fireball.png";
        };
    }

    private void initLabel(){
        label = new JLabel(icon);
        label.setSize(object.getSize());
        label.setLocation(object.getLocation());
    }

    public void updatePosition(Point position) {
        label.setLocation(position);
    }

}
