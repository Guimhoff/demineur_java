package app.View;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Menu extends JPanel {
    /* Main menu of the game (UI) */
    
    /* If a game is loaded and ready to be resumed */
    private static Boolean canResume = false;
    
    /**
     * Constructor
     */
    public Menu () {
    
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridy = 0;
        MenuButton _resumeButton = new MenuButton(this, "Reprendre la partie", "resume");
        _resumeButton.setEnabled(canResume);
        this.add(_resumeButton, gbc);

        gbc.gridy = 1;
        MenuButton _playButton = new MenuButton(this, "Nouvelle Partie", "newGame");
        this.add(_playButton, gbc);

        gbc.gridy = 2;
        MenuButton _quitButton = new MenuButton(this, "Quitter", "quit");
        this.add(_quitButton, gbc);
        
    }
    
    /**
     * Enables the resume button in the menu
     */
    public static void enableResume() {
        canResume = true;
        return;
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
            case "resume":
                resume();
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
    private void resume(){
        System.out.println("Reprendre la partie");
        Window.window.switchtoGame();
    }
    
    /**
     * Open the configuration window for a new game
     */
    private void newGame(){
        System.out.println("Nouvelle partie");
        Window.window.switchToSettings();
    }
}
