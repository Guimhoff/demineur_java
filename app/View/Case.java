package app.View;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Image;

import app.Modele.Position;
import app.Modele.Box;

public class Case extends JButton {
    
    
    public static final int size = 40;
    
    private Game _parent;
    private Position _pos;

    public Case(Game parent, Position pos) {
        super();

        _parent = parent;
        _pos = pos;
        
        
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        
        Dimension dim = new Dimension(size, size);
        
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                if(SwingUtilities.isRightMouseButton(e)){
                    _parent._map.grid()[_pos.get_y()][pos.get_x()].rightClick();;
                } else {
                    _parent._map.grid()[_pos.get_y()][pos.get_x()].onClick();
                }
                _parent.refresh();
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
        
        ImageIcon img = new ImageIcon();
        
        if (box.get_marked()) {
            img = icon("images/flagged.png");
            this.setIcon(img);
            return;
        }
        
        if (!box.get_discovered()) {
            img = icon("images/facingDown.png");
            this.setIcon(img);
            return;
        }
        
        
        String boxType = box.get_type();
        
        switch (boxType) {
            case Box.emptyBox:
                img = icon("images/0.png");
                this.setIcon(img);
                return;
            case Box.bombBox:
                img = icon("images/bomb.png");
                this.setIcon(img);
                return;
        }
        
        img = icon("images/" + box.closeBombs() + ".png");
        this.setIcon(img);
        return;
        
    }
    
    private ImageIcon icon(String filename){
        ImageIcon imageIcon = new ImageIcon(filename); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        return new ImageIcon(newimg);  // transform it back
    }
}
