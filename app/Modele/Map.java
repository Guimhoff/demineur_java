package app.Modele;

import java.util.Random;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    /* Class representing the map */
    
    /* Only live instance of map */
    public static Map map;
    
    /* Grid containing the boxes */
    private Box[][] _grid;
    /* length of the map */
    private final int _length;
    /* width of the map */
    private final int _width;
    
    /* number of bombs in the map */
    private final int _bombNumber;
    /* number of unreaveled boxes on the map */
    private int _hidedBoxes;
    /* number of boxes marked with a flag */
    private int _markedBoxes;
    
    /* List of positions of the neighbours of a position relatively to this position */
    private static ArrayList<Position> _potNeigh;
    
    /* Has the game been initialized (it is done after first click to avoid clicking on a bomb first) */
    private boolean _gameInitialized;
    /* Has the game ended */
    private boolean _gameOver;
    /* Has the player lost */
    private boolean _defeat;
    /* Has the player won */
    private boolean _victory;
    
    /**
     * Constructor of the map
     * @param length
     * @param width
     * @param bombNumber
     */
    public Map(int length, int width, int bombNumber) {
        _grid = new Box[length][width];
        _length = length;
        _width = width;

        _bombNumber = bombNumber;
        _hidedBoxes = _length * _width;
        _markedBoxes = 0;
        
        _defeat = false;
        _victory = false;
        
        _gameInitialized = false;
        _gameOver = false;
        
        preInit();
    }
    
    /**
     * Set the live map
     * @param map
     */
    public static void setMap(Map map) {
        Map.map = map;
    }
    
    /**
     * Initializes the map before puting the bombs in place
     */
    private void preInit() {
        // placement of empty boxes
        for (var y=0; y<_length; y++) {
            for (var x=0; x<_width; x++) {
                _grid[y][x] = new EmptyBox(x, y, this);
            }
        }
        
        return;
    }
    
    /**
     * Initializes the map by putting the bombs according to the first box clicked
     * (no bombBox or NumberBox should be placed there)
     * @param firstPos first box clicked by the user
     */
    private void GenerateMap(Position firstPos) {
        // placement of the bombs
        _gameInitialized = true;
        int i = 0;
        while (i < _bombNumber) {
            // picks a random position 
            int x = new Random().nextInt(_width);
            int y = new Random().nextInt(_length);

            Position bombPos = new Position(x, y);
            
            // checks if the position is not already occupied by a bomb or to close form the firstPos
            if (grid(bombPos).get_type() != Box.bombBox && !(bombPos.equals(firstPos)) && !neighbours(x, y).contains(firstPos)) {
                Box oldBox = grid(bombPos);
                _grid[y][x] = new BombBox(x, y, this);
                grid(bombPos).set_marked(oldBox.get_marked());
                i++;
                
                // each  bomb places 8 number next to its position, except if there is already a bomb
                // increments the number if there is already one
                for (Position pos : neighbours(x, y)) {
                    Box tempBox = grid(pos);
                    if (tempBox.get_type() != Box.bombBox) {
                        if (tempBox.get_type() == Box.numberBox)
                            tempBox.addCloseBomb();
                        else
                            setGrid(pos, new NumberBox(pos, this));
                            grid(pos).set_marked(tempBox.get_marked());
                    }
                }
            }
        }
    }
    
    /**
     * Left click on the given position of the grid
     * @param pos
     */
    public void leftClick(Position pos) {
        if(!_gameInitialized) GenerateMap(pos);
        grid(pos).onClick();
        return;
    }

    /**
     * Right click on the given position of the grid
     * @param pos
     */
    public void rightClick(Position pos) {
        grid(pos).rightClick();;
        return;
    }
    
    /**
     * Updates the number of hided boxes when revealing a box and checks for victory
     */
    public void unhideBox() {
        if (_gameOver) return;
        _hidedBoxes -= 1;
        if (_hidedBoxes == _bombNumber) {
            _victory = true;
            _gameOver = true;
        }
    }
    
    /**
     * Adds a box to _markedBoxes count
     */
    public void addMarkedBox() {
        _markedBoxes += 1;
        return;
    }

    /**
     * Removes a box from _markedBoxes count
     */
    public void removeMarkedBox() {
        _markedBoxes -= 1;
        return;
    }
    
    /**
     * Actions taken when a game is lost
     */
    public void GameLost() {
        _defeat = true;
        _gameOver = true;
        for (var y=0; y<_length; y++) {
            for (var x=0; x<_width; x++) {
                Box box = grid(new Position(x, y));
                if (box.get_type().hashCode() == Box.bombBox.hashCode()) {
                    box.reveal();
                }
            }
        }
        return;
    }
    
    /**
     * Getter of the boxes contained in the grid
     * @param pos
     * @return box at pos
     */
    public Box grid(Position pos) {
        return _grid[pos.get_y()][pos.get_x()];
    }
    
    /**
     * Getter of the boxes contained in the grid
     * @param x
     * @param y
     * @return box at x, y
     */
    public Box grid(int x, int y) {
        return _grid[y][x];
    }
    
    /**
     * Sets  the given box at the given position of the grid
     * @param pos
     * @param newBox
     */
    private void setGrid(Position pos, Box newBox) {
        _grid[pos.get_y()][pos.get_x()] = newBox;
        return;
    }
    
    /**
     * Getter of _length
     * @return _length
     */
    public int length() { return _length; }
    /**
     * Getter of _width
     * @return _width
     */
    public int width() { return _width; }
    
    /**
     * Getter of _gameOver
     * @return _gameOver
     */
    public boolean get_gameOver() {
        return _gameOver;
    }
    
    /**
     * Getter of _victory
     * @return _victory
     */
    public boolean get_victory() {
        return _victory;
    }
    
    /**
     * Getter of _defeat
     * @return _defeat
     */
    public boolean get_defeat() {
        return _defeat;
    }
    
    /**
     * Getter of _bombNumber
     * @return _bombNumber
     */
    public int get_bombNumber() {
        return _bombNumber;
    }
    
    /**
     * Getter of _markedBoxes
     * @return _markedBoxes
     */
    public int get_markedBoxes() {
        return _markedBoxes;
    }
    
    /**
     * States if the given pos is on the map
     * @param pos
     * @return true if yes, false if not
     */
    private boolean isOnMap (Position pos) {
        return (pos.get_x() >= 0 && pos.get_x() < _width && pos.get_y() >= 0 && pos.get_y() < _length);
    } 
    
    /**
     * Apply a pattern on the map at the given origin and retrives the list of the position which are on the map
     * @param origin
     * @param posList
     * @return list of positions
     */
    private ArrayList<Position> pattern (Position origin, ArrayList<Position> posList) {
        
        ArrayList<Position> out = new ArrayList<Position>();
        
        for (Position pos : posList) {
            Position temp = origin.add(pos);
            if (isOnMap(temp))
                out.add(temp);
        }
        
        return out;
    }
    
    /**
     * Initiates the pattern of potential neighbours
     */
    public static void initiatePotNeigh() {
        _potNeigh = new ArrayList<Position>();
        _potNeigh.add(new Position(0, 1));
        _potNeigh.add(new Position(1, 1));
        _potNeigh.add(new Position(1, 0));
        _potNeigh.add(new Position(1, -1));
        _potNeigh.add(new Position(0, -1));
        _potNeigh.add(new Position(-1, -1));
        _potNeigh.add(new Position(-1, 0));
        _potNeigh.add(new Position(-1, 1));
        return;
    }
    
    /**
     * Returns the neighbours of the given coordinates
     * @param x
     * @param y
     * @return list of poisitions (neighbours)
     */
    public ArrayList<Position> neighbours(int x, int y) {
        return pattern(new Position(x, y), _potNeigh);
    }
    
    /**
     * Returns the neighbours of the given position
     * @param pos
     * @return list of poisitions (neighbours)
     */
    public ArrayList<Position> neighbours(Position pos) {
        return pattern(pos, _potNeigh);
    }
    
}