package app.Modele;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Box implements Serializable {
    /* Abstract class representing a box which can contain a nothing, a bomb, or a number */

    public static final String emptyBox = "emptyBox";
    public static final String bombBox = "bombBox";
    public static final String numberBox = "numberBox";
    
    /* Type of box */
    private String _type;
    /* Position of the box on the map */
    protected Position _pos;
    /* Is the box marked with a flag */
    protected Boolean _marked;
    /* Is the box discovered */
    protected Boolean _discovered;
    /* Has the box exploded */
    protected Boolean _exploded;
    
    /**
     * Constructor using direct coordinates
     * @param type
     * @param x
     * @param y
     * @param map
     */
    Box(String type, int x, int y, Map map) {
        _type = type;
        _pos = new Position(x, y);
        _discovered = false;
        _marked = false;
    }
    
    /**
     * Constructor using Position
     * @param type
     * @param pos
     * @param map
     */
    Box(String type, Position pos, Map map) {
        _type = type;
        _pos = pos;
        _discovered = false;
        _marked = false;
    }
    
    /**
     * @return the _type
     */
    public String get_type() {
        return _type;
    }

    /**
     * @return the _pos
     */
    public Position get_pos() {
        return _pos;
    }

    /**
     * @return the _marked
     */
    public Boolean get_marked() {
        return _marked;
    }
    
    /**
     * set the _marked
     * @param bool
     */
    public void set_marked(boolean bool) {
        _marked = bool;
        return;
    }
    
    /**
     * @return the _discovered
     */
    public Boolean get_discovered() {
        return _discovered;
    }
    
    /**
     * Reveals the box
     */
    public void reveal() {
        _discovered = true;
        return;
    }
    
    /**
     * Returns the neighbours of the box
     * @return array of positions
     */
    public ArrayList<Position> neighbours() {
        return Map.map.neighbours(_pos);
    }
    
    /**
     * Actions executed when left-clicking on the undiscovered box, depending of the box type
     */
    abstract protected void clickAction();
    
    /**
     * Actions executed when left-clicking on the box, whatever is the box type
     */
    public void onClick() {
        if (Map.map.get_gameOver()) return;
        if (_discovered) return;
        if (_marked) return;
        
        clickAction();
        Map.map.unhideBox();
        return;
    }
    
    /**
     * Actions executed when right-clicking on the box (indentical for each box type)
     */
    public void rightClick() {
        if (Map.map.get_gameOver()) return;
        if (_discovered) return;
        
        _marked = !_marked;
        
        if (_marked)
            Map.map.addMarkedBox();
        else
            Map.map.removeMarkedBox();
        
        return;
    }
    
    /**
     * Add neighbouring bomb to the counter for NumberBox, empty for other types
     */
    public void addCloseBomb() {}

    /**
     * Returns the number of close bombs for numberBox, -1 for other types
     * @return number of close bombs, or -1
     */
    public int closeBombs() {
        return -1;
    }
}