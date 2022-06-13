package app.Modele;

public class EmptyBox extends Box {
    /* Box containing nothing */
    
    /**
     * Constructor using direct coordinates
     * @param x
     * @param y
     * @param map
     */
    EmptyBox(int x, int y, Map map) {
        super(Box.emptyBox, x, y, map);
    }
    
    /**
     * Constructor using position
     * @param pos
     * @param map
     */
    EmptyBox(Position pos, Map map) {
        super(Box.emptyBox, pos, map);
    }

    /**
     * Actions executed when left-clicking on the undiscovered box
     */
    protected void clickAction() {
        _discovered = true;
            
        for (Position pos: neighbours()) {
            Box box = Map.map.grid(pos);
            if (!box._discovered) {
                Map.map.grid(pos).onClick();   
            }
        } 
        return;
    }
}