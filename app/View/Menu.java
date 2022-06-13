package app.View;

import javax.swing.JPanel;

import app.Modele.Map;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Menu extends JPanel {
    /* Main menu of the game (UI) */
    
    /* Parent window */
    private Window _parent;
    
    /**
     * Constructor
     * @param parent
     */
    public Menu (Window parent) {
        _parent = parent;
    
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridy = 0;
        MenuButton _quickButton = new MenuButton(this, "Partie rapide", "quickGame");
        this.add(_quickButton, gbc);

        gbc.gridy = 1;
        MenuButton _playButton = new MenuButton(this, "Nouvelle Partie", "newGame");
        this.add(_playButton, gbc);

        gbc.gridy = 2;
        MenuButton _quitButton = new MenuButton(this, "Quitter", "quit");
        this.add(_quitButton, gbc);
        
    }
    
    /**
     * Executes a function according to the given String
     * @param function
     */
    public void execute(String function){
        switch (function){
            case "quit":
                quit();
                break;
            case "quickGame":
                quickGame();
                break;
            case "newGame":
                newGame();
                break;
        }
    }
    
    /**
     * Quits the game
     */
    private void quit(){
        System.exit(0);
    }
    
    /**
     * Launch a quick game
     */
    private void quickGame(){
        System.out.println("Partie Rapide");
        _parent.switchtoGame(new Map(20, 20, 100));
    }
    
    /**
     * Open the configuration window for a new game
     */
    private void newGame(){
        System.out.println("Nouvelle partie");
        _parent.switchToSettings();
    }
}
