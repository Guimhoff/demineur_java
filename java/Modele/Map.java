package java.Modele;

import java.util.Random;

public class Map {

    private Box[][] _grid;
    private final int _length;
    private final int _width;

    private final int _bombNumber;


    Map(int length, int width, int bombNumber) {
        _grid = new Box[width][length];
        _length = length;
        _width = width;

        _bombNumber = bombNumber;

        GenerateMap();
    }

    private void GenerateMap() {
        // placement des cases vides
        // placement des bombes et chaque bombe place autour d'elle 8 nombres (sauf s'il y a une bombe)

        for (var y=0; y<_width; y++) {
            for (var x=0; x<_length; x++) {
                _grid[y][x] = new EmptyBox(x, y);
            }
        }

        int i = 0;
        while (i < _bombNumber) {
            int x = new Random().nextInt(_length);
            int y = new Random().nextInt(_width);

            if (_grid[y][x].get_type() != Box.bombBox) {
                _grid[y][x] = new BombBox(x, y);
                i++;
            }
        }


    }
}