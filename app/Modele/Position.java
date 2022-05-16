package app.Modele;

public class Position {

    private int _x;
    private int _y;

    Position(int x, int y) {
        _x = x;
        _y = y;
    }

    /**
     * @return the _x
     */
    public int get_x() {
        return _x;
    }

    /**
     * @return the _y
     */
    public int get_y() {
        return _y;
    }
    
    public Position add(Position pos) {
        return new Position(_x + pos.get_x(), _y + pos.get_y());
    }

}