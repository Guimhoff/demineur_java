package app.View;

import javax.swing.*;
import java.awt.event.*;
import app.Modele.Map;

public class Fenetre extends JFrame {
    
    JPanel _activeWindow;
    
    public Fenetre() {
        super("Démineur");
        
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
               System.exit(0);
            }
        };
        
        addWindowListener(l);
        
        _activeWindow = new JPanel();
        switchToMenu();
        
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(900,900);
        

        Thread thread = new Thread(){
            public void run(){
                Case.lateInit();
            }
        };
        thread.start();
    }
    
    public void switchToMenu(){
        _activeWindow.setVisible(false);
        _activeWindow = new Menu(this);
        setContentPane(_activeWindow);
    }
    
    public void switchToSettings(){
        _activeWindow.setVisible(false);
        _activeWindow = new GameSettings(this);
        setContentPane(_activeWindow);
    }
    
    public void switchtoGame(Map map){
        _activeWindow.setVisible(false);
        _activeWindow = new Game(this, map);
        setContentPane(_activeWindow);
    }
}
