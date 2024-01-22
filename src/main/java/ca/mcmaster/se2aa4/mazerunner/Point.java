package ca.mcmaster.se2aa4.mazerunner;

public class Point {
    
    int x_pos;
    int y_pos;

    Point (int x, int y) {
        this.x_pos = x;
        this.y_pos = y;
    }

    public void changePosition (int new_x, int new_y) {
        this.x_pos = new_x;
        this.y_pos = new_y;
    }
}
