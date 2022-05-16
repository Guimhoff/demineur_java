package app.Modele;



public abstract class Box {

    public static String emptyBox = "emptyBox";
    public static String bombBox = "bombBox";
    public static String numberBox = "numberBox";

    private String _type;
    protected Position _pos;
    protected Boolean _marked;
    protected Boolean _discovered;
    
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

    abstract protected void clickAction();
    
    public void onClick() {
        if (_discovered) return;
        if (_marked) return;
        clickAction();
    }
    
    public void rightClick() {
        if (_discovered)
            return;
        
        _marked = !_marked;
    }
    
    public void addCloseBomb() {}
}