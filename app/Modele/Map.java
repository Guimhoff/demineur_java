package app.Modele;

import java.util.Random;
import java.util.ArrayList;

public class Map {

    private Box[][] _grid;
    private final int _length;
    private final int _width;

    private final int _bombNumber;
    private int _hidedBoxes;
    private int _markedBoxes;

    private ArrayList<Position> _potNeigh;
    private ArrayList<Position> _dirPotNeigh;
    
    private boolean _gameInitialized;
    private boolean _gameOver;
    private boolean _defeat;
    private boolean _victory;
    

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
        
        initiateDirPotNeigh();
        initiatePotNeigh();
        preInit();
    }
    
    private void preInit() {
        // placement des cases vides
        for (var y=0; y<_length; y++) {
            for (var x=0; x<_width; x++) {
                _grid[y][x] = new EmptyBox(x, y, this);
            }
        }
        
        return;
    }
    
    
    private void GenerateMap(Position firstPos) {
        // placement des bombes et chaque bombe place autour d'elle 8 nombres (sauf s'il y a une bombe)
        _gameInitialized = true;
        int i = 0;
        while (i < _bombNumber) {
            int x = new Random().nextInt(_width);
            int y = new Random().nextInt(_length);

            Position bombPos = new Position(x, y);
            
            if (grid(bombPos).get_type() != Box.bombBox && !(bombPos.equals(firstPos)) && !neighbours(x, y).contains(firstPos)) {
                Box oldBox = grid(bombPos);
                _grid[y][x] = new BombBox(x, y, this);
                grid(bombPos).set_marked(oldBox.get_marked());
                i++;
                
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
    
    public void leftClick(Position pos) {
        if(!_gameInitialized) GenerateMap(pos);
        grid(pos).onClick();
        return;
    }
    
    public void rightClick(Position pos) {
        grid(pos).rightClick();;
        return;
    }
    
    public void unhideBox() {
        if (_gameOver) return;
        _hidedBoxes -= 1;
        if (_hidedBoxes == _bombNumber) {
            _victory = true;
            _gameOver = true;
        }
    }

    public void addMarkedBox() {
        _markedBoxes += 1;
        return;
    }
    
    public void removeMarkedBox() {
        _markedBoxes -= 1;
        return;
    }
    
    public void GameLost() {
        _defeat = true;
        _gameOver = true;
        for (var y=0; y<_length; y++) {
            for (var x=0; x<_width; x++) {
                Box box = grid(new Position(x, y));
                if (box.get_type() == Box.bombBox) {
                    box.reveal();
                }
            }
        }
        return;
    }
    
    public Box[][] grid() {
        return _grid;
    }
    
    public Box grid(Position pos) {
        return _grid[pos.get_y()][pos.get_x()];
    }
    
    public Box grid(int x, int y) {
        return _grid[y][x];
    }
    
    private void setGrid(Position pos, Box newBox) {
        _grid[pos.get_y()][pos.get_x()] = newBox;
    }
    
    public int length() { return _length; }
    public int width() { return _width; }
    
    public boolean get_gameOver() {
        return _gameOver;
    }
    
    public boolean get_victory() {
        return _victory;
    }
    
    public boolean get_defeat() {
        return _defeat;
    }
    
    public int get_bombNumber() {
        return _bombNumber;
    }
    
    public int get_markedBoxes() {
        return _markedBoxes;
    }
    
    private boolean isOnMap (Position pos) {
        return (pos.get_x() >= 0 && pos.get_x() < _width && pos.get_y() >= 0 && pos.get_y() < _length);
    } 

    private ArrayList<Position> pattern (Position origin, ArrayList<Position> posList) {
        
        ArrayList<Position> out = new ArrayList<Position>();
        
        for (Position pos : posList) {
            Position temp = origin.add(pos);
            if (isOnMap(temp))
                out.add(temp);
        }
        
        return out;
    }
    
    private void initiatePotNeigh() {
        _potNeigh = new ArrayList<Position>();
        _potNeigh.add(new Position(0, 1));
        _potNeigh.add(new Position(1, 1));
        _potNeigh.add(new Position(1, 0));
        _potNeigh.add(new Position(1, -1));
        _potNeigh.add(new Position(0, -1));
        _potNeigh.add(new Position(-1, -1));
        _potNeigh.add(new Position(-1, 0));
        _potNeigh.add(new Position(-1, 1));
        System.out.println("potNeigh initialisé");
    }
    
    private void initiateDirPotNeigh() {
        _dirPotNeigh = new ArrayList<Position>();
        _dirPotNeigh.add(new Position(0, 1));
        _dirPotNeigh.add(new Position(1, 0));
        _dirPotNeigh.add(new Position(0, -1));
        _dirPotNeigh.add(new Position(-1, 0));
        System.out.println("dirPotNeigh initialisé");
    }

    public ArrayList<Position> neighbours(int x, int y) {
        return pattern(new Position(x, y), _potNeigh);
    }
    
    public ArrayList<Position> neighbours(Position pos) {
        return pattern(pos, _potNeigh);
    }
    
    public ArrayList<Position> directNeighbours(Position pos) {
        return pattern(pos, _dirPotNeigh);
    }
}