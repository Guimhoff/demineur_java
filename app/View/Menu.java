package app.View;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class Menu extends JPanel {
    
    public Menu () {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        this.add(Box.createHorizontalStrut(10));
        
        MenuButton _continueButton = new MenuButton("Continuer");
        this.add(_continueButton);
        
        MenuButton _playButton = new MenuButton("Nouvelle Partie");
        this.add(_playButton);
        
        MenuButton _quitButton = new MenuButton("Quitter");
        this.add(_quitButton);

        this.add(Box.createHorizontalStrut(10));

        
        
        //this.add(new JSeparator());
    }
}
