package app.View;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import app.Modele.Position;
import app.Modele.Box;

public class Case extends JButton {

    private static int _size = 32;
    
    private static final Map<String, String> iconPaths = Map.ofEntries(
        Map.entry("hided", "images/facingDown.png"),
        Map.entry("flag", "images/flagged.png"),
        Map.entry("bomb", "images/bomb.png"),
        Map.entry("empty", "images/0.png"),
        Map.entry("1", "images/1.png"),
        Map.entry("2", "images/2.png"),
        Map.entry("3", "images/3.png"),
        Map.entry("4", "images/4.png"),
        Map.entry("5", "images/5.png"),
        Map.entry("6", "images/6.png"),
        Map.entry("7", "images/7.png"),
        Map.entry("8", "images/8.png")
    );
    
    private static Map<String, Icon> icons = new HashMap<>();
    
    private Game _parent;
    private Position _pos;
    
    private String icon;

    public Case(Game parent, Position pos) {
        super();

        _parent = parent;
        _pos = pos;
        
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        
        Dimension dim = new Dimension(_size, _size);
        
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    _parent._map.rightClick(pos);
                    refresh();
                } else {
                    _parent._map.leftClick(pos);
                    String boxType = _parent._map.grid()[_pos.get_y()][pos.get_x()].get_type();
                    if (boxType == Box.emptyBox || boxType == Box.bombBox)
                        _parent.refresh();
                    else
                        refresh();
                }
                _parent.refreshUI();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
    }
    
    public void refresh(){
        Box box = _parent._map.grid(_pos);
        
        if (box.get_marked()) {
            changeIcon("flag");
            return;
        }
        
        if (!box.get_discovered()) {
            changeIcon("hided");
            return;
        }
        
        
        String boxType = box.get_type();
        
        switch (boxType) {
            case Box.emptyBox:
                changeIcon("empty");
                return;
            case Box.bombBox:
                changeIcon("bomb");
                return;
        }
        
        changeIcon(Integer.toString(box.closeBombs()));
        return;
        
    }
    
    private void changeIcon(String newIcon){
        if (icon == newIcon) return;
        
        addIcon(newIcon);
        
        this.setIcon(icons.get(newIcon));
        
        return;
    }
    
    private static ImageIcon genIcon(String filename) {
        ImageIcon imageIcon = new ImageIcon(filename); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        int presentSize = 2048;
        
        while(presentSize > _size*2) {
            presentSize /= 2;
            image = image.getScaledInstance(presentSize, presentSize, java.awt.Image.SCALE_SMOOTH);
        }
        
        image = image.getScaledInstance(_size, _size, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
    
    public static void lateInit() {
        
        for (String key : iconPaths.keySet()) {
            addIcon(key);
        }
    }
    
    private static void addIcon(String name) {
        if(!icons.containsKey(name))
            icons.put(name, genIcon(iconPaths.get(name)));
    }
}
