package app.View;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Box;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import app.Modele.Map;

public class GameSettings extends JPanel {
    /* UI displayed when configuring a new game */
    
    /* length saved in the GameSettings panel */
    private static int _length = 15;
    /* width saved in the GameSettings panel */
    private static int _width = 20;
    /* bombs percent saved in the GameSettings panel */
    private static int _bombPercent = 20;
    
    /* file where settings are saved */
    private static final String _file = "GameSettings.json";
    
    
    /* play button */
    private JButton _playButton;
    /* slider setting the length */
    private JSlider _lengthSlider;
    /* Label showing the length */
    private JLabel _lengthLab;
    /* slider setting the width */
    private JSlider _widthSlider;
    /* Label showing the width */
    private JLabel _widthLab;
    /* slider setting the bomb percentage */
    private JSlider _bombsSlider;
    /* Label showing the bomb percentage */
    private JLabel _bombsLab;
    
    /**
     * Constructor
     */
    GameSettings(){
        drawInterface();
    }
    
    /**
     * Draws the interface and maps sliders and button to actions
     */
    private void drawInterface(){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton _backButton = new JButton("Retour au menu");
        this.add(_backButton, gbc);
        
        _backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retour au menu");
                Window.window.switchToMenu();
            }
        });

        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 200 ), gbc );
        
        gbc.gridy += 1;
        
        _lengthSlider = new JSlider(5, 20, _length);
        this.add(_lengthSlider, gbc);

        _lengthLab = new JLabel(_lengthSlider.getValue() + " lignes");
        this.add(_lengthLab, gbc);
        
        _lengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lengthTxt();
                bombsTxt();
                updateSettings();
            }
        });
        

        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 50 ), gbc );
        
        gbc.gridy += 1;

        _widthSlider = new JSlider(5, 40, _width);
        this.add(_widthSlider, gbc);

        _widthLab = new JLabel(_widthSlider.getValue() + " colonnes");
        this.add(_widthLab, gbc);

        _widthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                widthTxt();
                bombsTxt();
                updateSettings();
            }
        });
        
        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 50 ), gbc );
        
        gbc.gridy += 1;

        _bombsSlider = new JSlider(10, 50, _bombPercent);
        this.add(_bombsSlider, gbc);
        
        _bombsLab = new JLabel(_bombsSlider.getValue() + " % - " + nbBombs() + " bombes");
        this.add(_bombsLab, gbc);

        _bombsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                bombsTxt();
                updateSettings();
            }
        });
        
        gbc.gridy += 1;
        
        this.add( Box.createVerticalStrut( 100 ), gbc );
        
        gbc.gridy += 1;

        _playButton = new JButton("Lancer la partie");
        this.add(_playButton, gbc);
        
        _playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lancement de la partie");
                Window.window.switchtoGame(new Map(_lengthSlider.getValue(), _widthSlider.getValue(),
                    nbBombs()));
            }
        });
        
        lengthTxt();
        widthTxt();
        bombsTxt();        
    }
    
    /**
     * Calculates the number of bombs presents on the map according to bomb percentage and map size
     * @return bomb number
     */
    private int nbBombs() {
        return _bombsSlider.getValue() * _lengthSlider.getValue() * _widthSlider.getValue() / 100;
    }
    
    /**
     * Updates length label
     */
    private void lengthTxt() {
        _lengthLab.setText(_lengthSlider.getValue() + " lignes");
        return;
    }

    /**
     * Updates width label
     */
    private void widthTxt() {
        _widthLab.setText(_widthSlider.getValue() + " colonnes");
        return;
    }

    /**
     * Updates bombs label
     */
    private void bombsTxt() {
        _bombsLab.setText(_bombsSlider.getValue() + " % - " + nbBombs() + " bombes / " 
        + (_widthSlider.getValue() * _lengthSlider.getValue()) + " cases");
        return;
    }
    
    /**
     * Updates settings if some were saved
     */
    private void updateSettings() {
        _length = _lengthSlider.getValue();
        _width = _widthSlider.getValue();
        _bombPercent = _bombsSlider.getValue();
        
        Thread thread1 = new Thread(){
            public void run(){
                SaveSettings();
            }
        };
        thread1.start();
        return;
    }

    /**
     * Saves the settings
     */
    private static void SaveSettings() {
        try {
            Path path = Paths.get(Window.saveDirectory());
            Files.createDirectories(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try (FileOutputStream fos = new FileOutputStream(Window.saveDirectory() + _file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            SaveSettings settings = new SaveSettings(_length, _width, _bombPercent);

            oos.writeObject(settings);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    /**
     * Loads the settings
     */
    public static void loadSettings() {
        try (FileInputStream fis = new FileInputStream(Window.saveDirectory() + _file);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
    
            try {
                SaveSettings settings = (SaveSettings) ois.readObject();
                
                _length = settings._length();
                _width = settings._widht();
                _bombPercent = settings._bombPercent();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
   }
}
