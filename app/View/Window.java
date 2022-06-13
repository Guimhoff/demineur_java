package app.View;

import javax.swing.*;
import java.awt.event.*;
import app.Modele.Map;

public class Window extends JFrame {
    /* UI window of the game */
    
    /* active panel */
    JPanel _activeWindow;
    
    /* unique instance of Window */
    public static Window window;
    
    /**
     * Constructor
     */
    public Window() {
        super("Démineur");
        window = this;
        
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
               System.exit(0);
            }
        };
        
        addWindowListener(l);
        
        _activeWindow = new JPanel();
        Game.Load();
        switchToMenu();
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(900,900);
        

        Thread thread0 = new Thread(){
            public void run(){
                Map.initiatePotNeigh();
            }
        };
        thread0.start();
        Thread thread1 = new Thread(){
            public void run(){
                Case.earlyInit();
            }
        };
        thread1.start();
        Thread thread2 = new Thread(){
            public void run(){
                GameSettings.loadSettings();
            }
        };
        thread2.start();
    }
    
    /**
     * Switch the panel displayed to menu
     */
    public void switchToMenu(){
        _activeWindow.setVisible(false);
        _activeWindow = new Menu();
        setContentPane(_activeWindow);
    }

    /**
     * Switch the panel displayed to game configuration
     */
    public void switchToSettings(){
        _activeWindow.setVisible(false);
        _activeWindow = new GameSettings();
        setContentPane(_activeWindow);
    }

    /**
     * Switch the panel displayed to game, providing a new game
     */
    public void switchtoGame(Map map){
        _activeWindow.setVisible(false);
        _activeWindow = new Game(map);
        setContentPane(_activeWindow);
    }

    /**
     * Switch the panel displayed to game
     */
    public void switchtoGame(){
        _activeWindow.setVisible(false);
        _activeWindow = new Game();
        setContentPane(_activeWindow);
    }
    
    /**
     * Get the save directory
     * @return String containing save directory
     */
    public static String saveDirectory() {
        return System.getProperty("java.io.tmpdir") + "Demineur" + System.getProperty("file.separator");
    }
}
