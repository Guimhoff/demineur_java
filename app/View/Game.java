package app.View;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import app.Modele.*;

public class Game extends JPanel {
    /* UI displayed when playing */
    
    /* Parent window */
    public Window _parent;
        
    /* Map of the game */
    public Map _map;
    
    /* CaseGrid displayed */
    private CaseGrid _caseGrid;
    /* HeadGameUI displayed */
    private HeadGameUI _headUI;

    /**
     * Constructor
     * @param parent
     * @param map
     */
    public Game(Window parent, Map map){
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
    
    /**
     * Getter of _map
     * @return _map
     */
    public Map get_map() {
        return _map;
    }
    
    /**
     * Refreshes all the _caseGrid
     */
    public void refresh() {
        _caseGrid.refresh();
        return;
    }
    
    /**
     * Refreshes the _headUI
     */
    public void refreshUI() {
        _headUI.refresh();
        return;
    }
}
