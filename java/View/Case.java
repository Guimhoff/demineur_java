package java.View;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.Insets;

public class Case extends JButton {
    Case() {
        super();

        ImageIcon img = new ImageIcon("images/flagged.png");
        this.setIcon(img);

        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
    }
}
