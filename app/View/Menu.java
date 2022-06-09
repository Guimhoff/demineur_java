package app.View;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Menu extends JPanel {
    
    private Fenetre _parent;
    
    public Menu (Fenetre parent) {
        _parent = parent;
    
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridy = 0;
        MenuButton _continueButton = new MenuButton(this, "Continuer", "resume");
        this.add(_continueButton, gbc);

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
            case "resume":
                resume();
                break;
            case "newGame":
                newGame();
                break;
        }
    }
    
    private void quit(){
        System.exit(0);
    }
    
    private void resume(){
        System.out.println("Reprise de la partie");
        //_parent.switchtoGame();
    }
    
    private void newGame(){
        System.out.println("Nouvelle partie");
        _parent.switchToSettings();
    }
}
