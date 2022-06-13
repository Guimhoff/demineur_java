package app.View;

import javax.swing.JPanel;

import app.Modele.Map;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Menu extends JPanel {
    
    private Fenetre _parent;
    
    public Menu (Fenetre parent) {
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
    
    private void quit(){
        System.exit(0);
    }
    
    private void quickGame(){
        System.out.println("Partie Rapide");
        _parent.switchtoGame(new Map(20, 20, 100));
    }
    
    private void newGame(){
        System.out.println("Nouvelle partie");
        _parent.switchToSettings();
    }
}
