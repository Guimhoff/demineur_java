package app.Modele;



public abstract class Box {

    public static String emptyBox = "emptyBox";
    public static String bombBox = "bombBox";
    public static String numberBox = "numberBox";

    private String _type;
    private Position _pos;
    private Boolean _marked;
    private Boolean _discovered;

    Box(String type, int x, int y) {
        _type = type;
        _pos = new Position(x, y);
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
     * @return the _discovered
     */
    public Boolean get_discovered() {
        return _discovered;
    }

    abstract void onClick();
    
}