package app.Modele;
import java.util.ArrayList;


public abstract class Box {

    public static final String emptyBox = "emptyBox";
    public static final String bombBox = "bombBox";
    public static final String numberBox = "numberBox";

    private String _type;
    protected Position _pos;
    protected Boolean _marked;
    protected Boolean _discovered;
    protected Boolean _exploded;
    
    protected Map _map;
    
    Box(String type, int x, int y, Map map) {
        _type = type;
        _pos = new Position(x, y);
        _discovered = false;
        _marked = false;
        _map = map;
    }
    
    Box(String type, Position pos, Map map) {
        _type = type;
        _pos = pos;
        _discovered = false;
        _marked = false;
        _map = map;
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
     * @return the _discovered
     */
    public Boolean get_discovered() {
        return _discovered;
    }
    
    public void reveal() {
        _discovered = true;
        return;
    }
    
    public ArrayList<Position> neighbours() {
        return _map.neighbours(_pos);
    }

    public ArrayList<Position> directNeighbours() {
        return _map.directNeighbours(_pos);
    }

    abstract protected void clickAction();
    
    public void onClick() {
        if (_map.get_gameOver()) return;
        if (_discovered) return;
        if (_marked) return;
        
        clickAction();
    }
    
    public void rightClick() {
        if (_map.get_gameOver()) return;
        if (_discovered) return;
        
        _marked = !_marked;
    }
    
    public void addCloseBomb() {}

    public int closeBombs() {
        return -1;
    }
}