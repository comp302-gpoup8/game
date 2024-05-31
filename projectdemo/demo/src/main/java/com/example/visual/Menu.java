package com.example.visual;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.example.App;

public abstract class Menu {
    
    public JPanel panel;
    public Dimension size;
    public App app;

    public Menu (int width, int height){
        this.size = new Dimension(width, height);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    
}
