package app.Modele;

import java.util.Random;
import java.util.ArrayList;

public class Map {

    private Box[][] _grid;
    private final int _length;
    private final int _width;

    private final int _bombNumber;

    private ArrayList<Position> _potNeigh;
    private ArrayList<Position> _dirPotNeigh;
    
    private boolean _gameOver;


    Map(int length, int width, int bombNumber) {
        _grid = new Box[width][length];
        _length = length;
        _width = width;

        _bombNumber = bombNumber;
        
        _gameOver = false;
        
        initiateDirPotNeigh();
        initiatePotNeigh();
        GenerateMap();
    }

    private void GenerateMap() {
        // placement des cases vides
        // placement des bombes et chaque bombe place autour d'elle 8 nombres (sauf s'il y a une bombe)

        for (var y=0; y<_width; y++) {
            for (var x=0; x<_length; x++) {
                _grid[y][x] = new EmptyBox(x, y, this);
            }
        }

        int i = 0;
        while (i < _bombNumber) {
            int x = new Random().nextInt(_length);
            int y = new Random().nextInt(_width);

            if (_grid[y][x].get_type() != Box.bombBox) {
                _grid[y][x] = new BombBox(x, y, this);
                i++;
                
                for (Position pos : neighbours(x, y)) {
                    Box tempBox = grid(pos);
                    if (tempBox.get_type() != Box.bombBox) {
                        if (tempBox.get_type() == Box.numberBox)
                            tempBox.addCloseBomb();
                        else
                            setGrid(pos, new NumberBox(pos, this));
                    }
                }
            }
        }
        
        

    }
    
    public void GameLost() {
        _gameOver = true;
        for (var y=0; y<_width; y++) {
            for (var x=0; x<_length; x++) {
                Box box = grid(new Position(x, y));
                if (box.get_type() == Box.bombBox) {
                    box.reveal();
                }
            }
        }
        return;
    }
    
    public Box grid(Position pos) {
        return _grid[pos.get_y()][pos.get_x()];
    }
    
    private void setGrid(Position pos, Box newBox) {
        _grid[pos.get_y()][pos.get_x()] = newBox;
    }
    
    public boolean get_gameOver() {
        return _gameOver;
    }
    
    private boolean isOnMap (Position pos) {
        return (pos.get_x() >= 0 && pos.get_x() < _length && pos.get_y() >= 0 && pos.get_y() < _width);
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
        ArrayList<Position> _potNeigh = new ArrayList<Position>();
        _potNeigh.add(new Position(0, 1));
        _potNeigh.add(new Position(1, 1));
        _potNeigh.add(new Position(1, 0));
        _potNeigh.add(new Position(1, -1));
        _potNeigh.add(new Position(0, -1));
        _potNeigh.add(new Position(-1, -1));
        _potNeigh.add(new Position(-1, 0));
        _potNeigh.add(new Position(-1, 1));
    }
    
    private void initiateDirPotNeigh() {
        ArrayList<Position> _dirPotNeigh = new ArrayList<Position>();
        _dirPotNeigh.add(new Position(0, 1));
        _dirPotNeigh.add(new Position(1, 0));
        _dirPotNeigh.add(new Position(0, -1));
        _dirPotNeigh.add(new Position(-1, 0));
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