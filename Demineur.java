import javax.swing.ImageIcon;

import app.View.Window;

public class Demineur {
  /* Main class of the game */
  
  /**
   * main
   * @param argv
   */
  public static void main(String argv[]) {
    
    Window f = new Window();
    
    ImageIcon icon = new ImageIcon("images/bomb.png");
    f.setIconImage(icon.getImage());
    
    f.setVisible(true);
  }
  
}
