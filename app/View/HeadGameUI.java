package app.View;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeadGameUI extends JPanel {
    
    Game _parent;
    Fenetre _fenetre;
    
    private JLabel _gameState;
    private JLabel _bombCount;
    
    HeadGameUI(Game parent, Fenetre fenetre) {
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
