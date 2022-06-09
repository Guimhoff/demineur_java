package app.Modele;

public class NumberBox extends Box {
    
    private int _closeBombs;
    
    NumberBox(int x, int y, Map map) {
        super(Box.numberBox, x, y, map);
        _closeBombs = 1;
    }
    
    NumberBox(Position pos, Map map) {
        super(Box.numberBox, pos, map);
        _closeBombs = 1;
    }
    
    public int closeBombs() {
        return _closeBombs;
    }
    
    protected void clickAction() {
        _discovered = true;
    }
    
    public void addCloseBomb() {
        _closeBombs++;
    }
    
}