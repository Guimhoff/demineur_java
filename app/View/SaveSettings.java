package app.View;

import java.io.Serializable;

public class SaveSettings implements Serializable {
    /* Class used to save the last settings set in GameSettings */
    
    /* Same variables as those used GameSettings */
    private int _length;
    private int _widht;
    private int _bombPercent;
    
    /**
     * Constructor
     * @param length
     * @param width
     * @param bombPercent
     */
    SaveSettings(int length, int width, int bombPercent) {
        _length = length;
        _widht = width;
        _bombPercent = bombPercent;
    }
    
    /**
     * Getter of _length
     * @return _length
     */
    public int _length() {
        return _length;
    }

    /**
     * Getter of _widht
     * @return _widht
     */
    public int _widht() {
        return _widht;
    }

    /**
     * Getter of _bombPercent
     * @return _bombPercent
     */
    public int _bombPercent() {
        return _bombPercent;
    }
}
