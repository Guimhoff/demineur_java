package app.View;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButton extends JButton {

    

    public MenuButton (Menu parent, String text) {
        super(text);
        
        init();
    }
    
    public MenuButton (Menu parent, String text, String function) {
        super(text);
        init();    
        
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.execute(function);
            }
        });
    }
    
    
    private void init(){
        Dimension size = new Dimension(1000, 150);
        
        this.setPreferredSize(size);
        this.setMinimumSize(size);
    }

}
