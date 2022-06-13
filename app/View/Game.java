package app.View;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import app.Modele.*;

public class Game extends JPanel {

    public Fenetre _parent;
    
    public Map _map;
    
    private CaseGrid _caseGrid;
    private HeadGameUI _headUI;

    public Game(Fenetre parent, Map map){
        _parent = parent;
        _map = map;


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
                
        _headUI = new HeadGameUI(this, _parent);
        this.add(_headUI, gbc);
        
        gbc.gridy = 1;
        _caseGrid = new CaseGrid(this);
        this.add(_caseGrid, gbc);
        
        
        refresh();
        
    }
    
    public Map get_map() {
        return _map;
    }
    
    public void refresh() {
        _caseGrid.refresh();
        return;
    }
    
    public void refreshUI() {
        _headUI.refresh();
        return;
    }
}
