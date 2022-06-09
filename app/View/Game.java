package app.View;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import app.Modele.*;

public class Game extends JPanel {

    public Fenetre _parent;
    
    public Map _map;
    
    private CaseGrid caseGrid;

    public Game(Fenetre parent, Map map){
        _parent = parent;
        _map = map;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JButton _continueButton = new JButton("Jeu");
        this.add(_continueButton, gbc);
        
        gbc.gridy = 1;
        caseGrid = new CaseGrid(this);
        this.add(caseGrid, gbc);
        
        refresh();
    }
    
    public Map get_map() {
        return _map;
    }
    
    public void refresh() {
        caseGrid.refresh();
    }
}
