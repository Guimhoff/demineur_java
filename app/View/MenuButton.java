package app.View;

import javax.swing.JButton;
import java.awt.Dimension;

public class MenuButton extends JButton {
    
    public MenuButton (String text) {
        super(text);

        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        
        
    
        this.setPreferredSize(new Dimension(500, 150));
        
    }
    
}
