package app.View;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Box;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import app.Modele.Map;

public class GameSettings extends JPanel {
    
    private Fenetre _parent;
    
    private JButton _playButton;
    private JSlider _lengthSlider;
    private JLabel _lengthLab;
    private JSlider _widthSlider;
    private JLabel _widthLab;
    private JSlider _bombsSlider;
    private JLabel _bombsLab;
    
    GameSettings(Fenetre parent){
        _parent = parent;
        
        drawInterface();
    }
    
    private void drawInterface(){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton _backButton = new JButton("Retour au menu");
        this.add(_backButton, gbc);
        
        _backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retour au menu");
                _parent.switchToMenu();
            }
        });

        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 200 ), gbc );
        
        gbc.gridy += 1;
        
        _lengthSlider = new JSlider(5, 20);
        this.add(_lengthSlider, gbc);

        _lengthLab = new JLabel(_lengthSlider.getValue() + " lignes");
        this.add(_lengthLab, gbc);
        
        _lengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lengthTxt();
                bombsTxt();
            }
        });
        

        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 50 ), gbc );
        
        gbc.gridy += 1;

        _widthSlider = new JSlider(5, 40);
        this.add(_widthSlider, gbc);

        _widthLab = new JLabel(_widthSlider.getValue() + " colonnes");
        this.add(_widthLab, gbc);

        _widthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                widthTxt();
                bombsTxt();
            }
        });
        
        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 50 ), gbc );
        
        gbc.gridy += 1;

        _bombsSlider = new JSlider(10, 50);
        this.add(_bombsSlider, gbc);
        
        _bombsLab = new JLabel(_bombsSlider.getValue() + " % - " + nbBombs() + " bombes");
        this.add(_bombsLab, gbc);

        _bombsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                bombsTxt();
            }
        });
        
        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 100 ), gbc );
        
        gbc.gridy += 1;

        _playButton = new JButton("Lancer la partie");
        this.add(_playButton, gbc);
        
        _playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement de la partie");
                _parent.switchtoGame(new Map(_lengthSlider.getValue(), _widthSlider.getValue(),
                    nbBombs()));
            }
        });
        
        lengthTxt();
        widthTxt();
        bombsTxt();        
    }
    
    private int nbBombs() {
        return _bombsSlider.getValue() * _lengthSlider.getValue() * _widthSlider.getValue() / 100;
    }
    
    private void lengthTxt() {
        _lengthLab.setText(_lengthSlider.getValue() + " lignes");
        return;
    }
    
    private void widthTxt() {
        _widthLab.setText(_widthSlider.getValue() + " colonnes");
        return;
    }
    
    private void bombsTxt() {
        _bombsLab.setText(_bombsSlider.getValue() + " % - " + nbBombs() + " bombes / " 
        + (_widthSlider.getValue() * _lengthSlider.getValue()) + " cases");
        return;
    }
}
