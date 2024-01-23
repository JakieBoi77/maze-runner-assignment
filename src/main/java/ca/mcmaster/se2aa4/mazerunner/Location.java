package ca.mcmaster.se2aa4.mazerunner;

public class Location {
    
    int x_pos;
    int y_pos;
    Direction direction;

    Location (int new_x, int new_y, Direction new_direction) {
        this.x_pos = new_x;
        this.y_pos = new_y;
        this.direction = new_direction;
    }
    
}
