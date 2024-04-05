package ca.mcmaster.se2aa4.mazerunner.util;

public enum CardinalDirection {

    NORTH, EAST, SOUTH, WEST;

    public CardinalDirection right() {
        CardinalDirection newDirection;
        switch (this) {
            case NORTH:
                newDirection = EAST;
                break;
            case SOUTH:
                newDirection = WEST;
                break;
            case WEST:
                newDirection = NORTH;
                break;
            case EAST:
                newDirection = SOUTH;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        return newDirection;
    }

    public CardinalDirection left() {
        CardinalDirection newDirection;
        switch (this) {
            case NORTH:
                newDirection = WEST;
                break;
            case SOUTH:
                newDirection = EAST;
                break;
            case WEST:
                newDirection = SOUTH;
                break;
            case EAST:
                newDirection = NORTH;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        return newDirection;
    }
}