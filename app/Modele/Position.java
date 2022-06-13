package app.Modele;

import java.io.Serializable;

public class Position implements Serializable {
    /* Class representing a position on the map */

    /* x coordinate */
    private int _x;
    /* y coordinate */
    private int _y;
    
    /**
     * Constructor
     * @param x coordinate
     * @param y coordinate
     */
    public Position(int x, int y) {
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
    
    /**
     * Adds two positions
     * @param pos
     * @return this + pos
     */
    public Position add(Position pos) {
        return new Position(_x + pos.get_x(), _y + pos.get_y());
    }
    
    /**
     * Checks if the position equals the given object
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Position) && (this._x == ((Position) o).get_x()) && (this._y == ((Position) o).get_y());
    }
    
}