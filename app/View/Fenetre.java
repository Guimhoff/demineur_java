package app.View;

import javax.swing.*;

import java.awt.event.*;

public class Fenetre extends JFrame {
    
    public Fenetre() {
        super("Démineur");
        
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
               System.exit(0);
            }
        };
        
        addWindowListener(l);
        
        Menu menu = new Menu();
        
        setContentPane(menu);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(900,900);
        setVisible(true);
    }
}
