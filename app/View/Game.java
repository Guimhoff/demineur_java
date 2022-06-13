package app.View;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import app.Modele.*;

public class Game extends JPanel {
    /* UI displayed when playing */
    
    /* Save file name */
    public static final String _file = "savedGame.json";
    
    /* CaseGrid displayed */
    private CaseGrid _caseGrid;
    /* HeadGameUI displayed */
    private HeadGameUI _headUI;

    /**
     * Constructor
     */
    public Game() {
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
                    
        _headUI = new HeadGameUI(this);
        this.add(_headUI, gbc);
            
        gbc.gridy = 1;
        _caseGrid = new CaseGrid(this);
        this.add(_caseGrid, gbc);
        
        refresh();
    }

    /**
     * Constructor
     * @param parent
     * @param map
     */
    public Game(Map map){
        Map.map = map;
        Menu.enableResume();
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
                
        _headUI = new HeadGameUI(this);
        this.add(_headUI, gbc);
        
        gbc.gridy = 1;
        _caseGrid = new CaseGrid(this);
        this.add(_caseGrid, gbc);
        
        Save();
        
        refresh();
    }
    
    /**
     * Getter of _map
     * @return _map
     */
    public Map get_map() {
        return Map.map;
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
        // the game is saved in a separate thread to avoid slowing the game
        // It is saved after each action of the user
        Thread thread1 = new Thread(){
            public void run(){
                Save();
            }
        };
        thread1.start();
        return;
    }
    
    /**
     * Saves the game
     */
    private static void Save() {
        try {
            Path path = Paths.get(Window.saveDirectory());
            Files.createDirectories(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
        try (FileOutputStream fos = new FileOutputStream(Window.saveDirectory() + _file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(Map.map);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }

    /**
     * Loads the game
     */
    public static void Load() {
        try (FileInputStream fis = new FileInputStream(Window.saveDirectory() + _file);
        ObjectInputStream ois = new ObjectInputStream(fis)) {

            try {
                Map.map = (Map) ois.readObject();
                Menu.enableResume();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
           System.out.println("Aucune map n'a pu être chargée");
        }
        return;
   }
}
