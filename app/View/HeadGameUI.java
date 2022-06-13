package app.View;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeadGameUI extends JPanel {
    /* Head of the game UI */
    
    /* Game UI */
    Game _parent;
    /* Window parent of the game */
    Window _fenetre;
    
    /* Label displaying victory or defeat */
    private JLabel _gameState;
    /* Label displaying number of placed flags and number of bombs */
    private JLabel _bombCount;
    
    /**
     * Constructor
     * @param parent
     * @param fenetre
     */
    HeadGameUI(Game parent, Window fenetre) {
        _parent = parent;
        _fenetre = fenetre;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JButton _backButton = new JButton("Retour au menu");
        this.add(_backButton, gbc);
        
        _backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retour au menu");
                _fenetre.switchToMenu();
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
        
        _bombCount.setText(_parent._map.get_markedBoxes() + "/" + _parent._map.get_bombNumber() + " bombes marquées");
        
        if (_parent._map.get_defeat()) {
            _gameState.setText("  Vous avez perdu !  ");
        } else if (_parent._map.get_victory()) {
            _gameState.setText("  Vous avez gagné !  ");
        } else {
            _gameState.setText("                     ");
        }
        
        return;
    }
    
}
