package app.Modele;


public class BombBox extends Box {
    /** Box containing a bomb */
    
    /**
     * Constructor using direct coordinates
     * @param x
     * @param y
     * @param map
     */
    BombBox(int x, int y, Map map) {
        super(Box.bombBox, x, y, map);
    }
    
    /**
     * Constructor using Position
     * @param pos
     * @param map
     */
    BombBox(Position pos, Map map) {
        super(Box.bombBox, pos, map);
    }
    
    /**
     * Actions executed when left-clicking on the undiscovered bomb
     */
    protected void clickAction() {
        _exploded = true;
        _map.GameLost();
        return;
    }
}