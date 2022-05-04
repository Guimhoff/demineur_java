import javax.swing.*;
import java.awt.event.*;

public class Fenetre extends JFrame {
    
    Fenetre() {
        super("Démineur");
        
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
               System.exit(0);
            }
         };
         
        addWindowListener(l);

        Case bouton = new Case();
  
        JPanel panneau = new JPanel();
        panneau.add(bouton);
        setContentPane(panneau);
  
        setSize(1000,1000);
        setVisible(true);
    }
}
