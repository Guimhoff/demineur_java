package app.Modele;

public class NumberBox extends Box {
    /* Box close to a bombox and containg a number */
    
    /* number of close bombs */
    private int _closeBombs;
    
    /**
     * Constructor using direct coordinates
     * @param x
     * @param y
     * @param map
     */
    NumberBox(int x, int y, Map map) {
        super(Box.numberBox, x, y, map);
        _closeBombs = 1;
    }
    
    /**
     * Constructor using Position
     * @param pos
     * @param map
     */
    NumberBox(Position pos, Map map) {
        super(Box.numberBox, pos, map);
        _closeBombs = 1;
    }
    
    /**
     * Returns the number of close bombs
     * @return number of close bombs
     */
    public int closeBombs() {
        return _closeBombs;
    }

    /**
     * Actions executed when left-clicking on the undiscovered box
     */
    protected void clickAction() {
        _discovered = true;
        return;
    }
    
    /**
     * Adds a bomb to _closeBombs
     */
    public void addCloseBomb() {
        _closeBombs++;
        return;
    }
    
}