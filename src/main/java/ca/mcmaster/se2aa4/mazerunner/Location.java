package ca.mcmaster.se2aa4.mazerunner;

public class Location {
    
    int x_pos;
    int y_pos;
    Direction direction;

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    Location (int x, int y, Direction direction) {
        this.x_pos = x;
        this.y_pos = y;
        this.direction = direction;
    }
}
