package app.View;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.Modele.*;

public class GameSettings extends JPanel {
    
    private Fenetre _parent;
    
    GameSettings(Fenetre parent){
        _parent = parent;
        
        drawInterface();
    }
    
    private void drawInterface(){
        JButton _playButton = new JButton("Lancer la partie");
        this.add(_playButton);
        _playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement de la partie");
                _parent.switchtoGame(new Map(10, 10, 10));
            }
        });
        
    }
}
