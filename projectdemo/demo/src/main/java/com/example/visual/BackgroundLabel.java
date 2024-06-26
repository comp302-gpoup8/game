package com.example.visual;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Background Label class
 * Pretty simple but GamePanel is too big so this improves readability a lot. 
 */
public class BackgroundLabel extends JLabel {

    protected ImageIcon backgroundImage;
    protected String DEFAULT_IMG_PATH = "projectdemo/demo/src/main/java/com/example/Graphical-Assets/200Background.png";

    public BackgroundLabel(){
        backgroundImage = new ImageIcon(DEFAULT_IMG_PATH);
        setIcon(backgroundImage);
    }
}
