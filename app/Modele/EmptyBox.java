package app.Modele;

public class EmptyBox extends Box {

    EmptyBox(int x, int y, Map map) {
        super(Box.emptyBox, x, y, map);
    }

    EmptyBox(Position pos, Map map) {
        super(Box.emptyBox, pos, map);
    }

    protected void clickAction() {
        
        _discovered = true;
        
    }
}