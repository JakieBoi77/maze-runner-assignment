package ca.mcmaster.se2aa4.mazerunner.util;

public class Coordinate {
    
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Coordinate get(CardinalDirection direction) {
        Coordinate coord;
        switch (direction) {
            case NORTH:
                coord = new Coordinate(this.x, this.y - 1);
                break;
            case EAST:
                coord = new Coordinate(this.x + 1, this.y);
                break;
            case SOUTH:
                coord = new Coordinate(this.x, this.y + 1);
                break;
            case WEST:
                coord = new Coordinate(this.x - 1, this.y);
                break;
            default:
                throw new IllegalStateException("Get new coordinate failed. Unexpected value: " + direction);
        }
        return coord;
    }
}
