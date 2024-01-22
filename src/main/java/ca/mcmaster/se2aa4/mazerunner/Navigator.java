package ca.mcmaster.se2aa4.mazerunner;

public class Navigator {
    
    Location location;
    Maze maze;

    Navigator(Location initial_location, Maze maze) {
        this.location = initial_location;
        this.maze = maze;
    }

}
