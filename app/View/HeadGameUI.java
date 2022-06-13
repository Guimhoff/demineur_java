package app.View;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Modele.Map;

public class HeadGameUI extends JPanel {
    /* Head of the game UI */
    
    /* Game UI */
    Game _parent;
    
    /* Label displaying victory or defeat */
    private JLabel _gameState;
    /* Label displaying number of placed flags and number of bombs */
    private JLabel _bombCount;
    
    /**
     * Constructor
     * @param parent
     */
    HeadGameUI(Game parent) {
        _parent = parent;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JButton _backButton = new JButton("Retour au menu");
        this.add(_backButton, gbc);
        
        _backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retour au menu");
                Window.window.switchToMenu();
            }
        });
        
        this.add( Box.createHorizontalStrut( 150 ) );

        _gameState = new JLabel("           ");
        this.add(_gameState, gbc);

        this.add( Box.createHorizontalStrut( 150 ) );

        _bombCount = new JLabel("0/0 bombes marquées");
        this.add(_bombCount, gbc);
        
        refresh();
    }
    
    /**
     * Refreshes the interface
     */
    public void refresh() {
        
        _bombCount.setText(Map.map.get_markedBoxes() + "/" + Map.map.get_bombNumber() + " bombes marquées");
        
        if (Map.map.get_defeat()) {
            _gameState.setText("  Vous avez perdu !  ");
        } else if (Map.map.get_victory()) {
            _gameState.setText("  Vous avez gagné !  ");
        } else {
            _gameState.setText("                     ");
        }
        
        return;
    }
    
}
