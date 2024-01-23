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

    public boolean checkWall(RelativeDirection relative_direction) {
        //Converts relative direction to an ordinal direction
        OrdinalDirection ordinal_direction;
        OrdinalDirection[] directions = OrdinalDirection.values();
        int currentIndex = this.location.direction.ordinal();
        switch (relative_direction) {
            case RIGHT:
                int nextIndex = (currentIndex + 1) % directions.length;
                ordinal_direction = directions[nextIndex];
                break;
            case FORWARD:
                ordinal_direction = directions[currentIndex];
                break;
            case LEFT:
                int previousIndex = (currentIndex - 1 + directions.length) % directions.length;
                ordinal_direction = directions[previousIndex];
                break;
            default:
                ordinal_direction = directions[currentIndex];
        }
        
        //Gets the maze tile that is in that direction
        Location new_location;
        switch (ordinal_direction) {
            case NORTH:
                new_location = new Location(this.location.x_pos, this.location.y_pos + 1, this.location.direction);
                break;
            case EAST:
                new_location = new Location(this.location.x_pos + 1, this.location.y_pos, this.location.direction);
                break;
            case SOUTH:
                new_location = new Location(this.location.x_pos, this.location.y_pos - 1, this.location.direction);
                break;
            case WEST:
                new_location = new Location(this.location.x_pos - 1, this.location.y_pos, this.location.direction);
                break;
            default:
                new_location = new Location(this.location.x_pos, this.location.y_pos, this.location.direction);
        }
        char tile = maze.getPointInfo(new_location);
        
        //Returns true if there is a wall
        if (tile == '#') {
            return true;
        } else {
            return false;
        }
    }

    public void moveForward() {
        Location new_location;
        switch (this.location.direction) {
            case NORTH:
                new_location = new Location(this.location.x_pos, this.location.y_pos + 1, this.location.direction);
                break;
            case EAST:
                new_location = new Location(this.location.x_pos + 1, this.location.y_pos, this.location.direction);
                break;
            case SOUTH:
                new_location = new Location(this.location.x_pos, this.location.y_pos - 1, this.location.direction);
                break;
            case WEST:
                new_location = new Location(this.location.x_pos - 1, this.location.y_pos, this.location.direction);
                break;
            default:
                new_location = new Location(this.location.x_pos, this.location.y_pos, this.location.direction);
        }
        char nextSpace = maze.getPointInfo(new_location);

        if (nextSpace == ' ') {
            logger.info("**** Moving to New Position: x = " + new_location.x_pos + ", y = " + new_location.x_pos + ", Direction = " + new_location.direction);
            this.location = new_location;
        } else {
            logger.error("**** Cannot move into a wall.");
        }
    }

    public void turnRight() {
        logger.info("**** Turning Right");
        OrdinalDirection[] directions = OrdinalDirection.values();
        int currentIndex = this.location.direction.ordinal();
        int nextIndex = (currentIndex + 1) % directions.length;
        this.location.direction = directions[nextIndex];
    }

    public void turnLeft() {
        logger.info("**** Turning Left");
        OrdinalDirection[] directions = OrdinalDirection.values();
        int currentIndex = this.location.direction.ordinal();
        int previousIndex = (currentIndex - 1 + directions.length) % directions.length;
        this.location.direction = directions[previousIndex];
    }
}
