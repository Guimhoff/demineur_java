package app.Modele;

public class BombBox extends Box {

    BombBox(int x, int y, Map map) {
        super(Box.bombBox, x, y, map);
    }
    
    BombBox(Position pos, Map map) {
        super(Box.bombBox, pos, map);
    }
    
    protected void clickAction() {
        _exploded = true;
        _map.GameLost();
    }
}