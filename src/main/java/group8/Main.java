package group8;

import javax.swing.SwingUtilities;

import group8.visual.App;

@SuppressWarnings("unused")
public class Main {
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(App::new);
    }
}
