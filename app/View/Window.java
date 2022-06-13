package app.View;

import javax.swing.*;
import java.awt.event.*;
import app.Modele.Map;

public class Window extends JFrame {
    /* UI window of the game */
    
    /* active panel */
    JPanel _activeWindow;
    
    /**
     * Constructor
     */
    public Window() {
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
                Case.earlyInit();
            }
        };
        thread.start();
    }
    
    /**
     * Switch the panel displayed to menu
     */
    public void switchToMenu(){
        _activeWindow.setVisible(false);
        _activeWindow = new Menu(this);
        setContentPane(_activeWindow);
    }

    /**
     * Switch the panel displayed to game configuration
     */
    public void switchToSettings(){
        _activeWindow.setVisible(false);
        _activeWindow = new GameSettings(this);
        setContentPane(_activeWindow);
    }

    /**
     * Switch the panel displayed to game
     */
    public void switchtoGame(Map map){
        _activeWindow.setVisible(false);
        _activeWindow = new Game(this, map);
        setContentPane(_activeWindow);
    }
}
