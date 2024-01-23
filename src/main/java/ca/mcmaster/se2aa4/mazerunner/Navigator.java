package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigator {
    
    private static final Logger logger = LogManager.getLogger();
    
    Location location;
    Maze maze;

    Navigator(Location initial_location, Maze maze) {
        this.location = initial_location;
        this.maze = maze;
    }

    public void moveForward() {
        Location new_location;
        switch (location.direction) {
            case NORTH:
                new_location = new Location(location.x_pos, location.y_pos + 1, location.direction);
                break;
            case EAST:
                new_location = new Location(location.x_pos + 1, location.y_pos, location.direction);
                break;
            case SOUTH:
                new_location = new Location(location.x_pos, location.y_pos - 1, location.direction);
                break;
            case WEST:
                new_location = new Location(location.x_pos - 1, location.y_pos, location.direction);
                break;
            default:
                new_location = new Location(location.x_pos, location.y_pos, location.direction);
        }
        char nextSpace = maze.getPointInfo(new_location);

        if (nextSpace == ' ') {
            logger.info("**** Moving to New Position: x = " + new_location.x_pos + ", y = " + new_location.x_pos + ", Direction = " + new_location.direction);
            location = new_location;
        } else {
            logger.error("**** Cannot move into a wall.");
        }
    }

    public void turnRight() {
        logger.info("**** Turning Right");
        Direction[] directions = Direction.values();
        int currentIndex = this.location.direction.ordinal();
        int nextIndex = (currentIndex + 1) % directions.length;
        this.location.direction = directions[nextIndex];
    }

    public void turnLeft() {
        logger.info("**** Turning Left");
        Direction[] directions = Direction.values();
        int currentIndex = this.location.direction.ordinal();
        int previousIndex = (currentIndex - 1 + directions.length) % directions.length;
        this.location.direction = directions[previousIndex];
    }
}
