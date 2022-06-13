package app.View;

import javax.swing.JPanel;

import app.Modele.Position;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

public class CaseGrid extends JPanel {
    
    public Game _parent;
    
    private ArrayList<Case> caseList;
    
    public CaseGrid(Game parent) {
        _parent = parent;
        
        caseList = new ArrayList<Case>();
        
        int sizeX = _parent._map.width();
        int sizeY = _parent._map.length();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        for (int y=0; y < sizeY; y++) {
            gbc.gridy = y;
            for (int x=0; x < sizeX; x++) {
                caseList.add(new Case(_parent, new Position(x, y)));
                this.add(caseList.get(caseList.size()-1), gbc);
            }
        }
        
    }
    
    public void refresh(){
        for (Case case1 : caseList) {
            case1.refresh();
        }
    }
    
}
