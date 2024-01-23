package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver {

    private static final Logger logger = LogManager.getLogger();

    public static Path solveMaze(Maze input_maze) {
        logger.info("**** Computing Path");
        
        char[][] maze = input_maze.maze_data;

        //Find maze start location
        int startRow = 0;
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0] == ' ') {
                startRow = row;
            }
        }
        Location startLocation = new Location(0, startRow, OrdinalDirection.EAST);
        logger.info("**** Start Location: x = " + startLocation.x_pos + ", y = " + startLocation.y_pos);

        //Find maze end location
        int endRow = 0;
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][maze[0].length - 1] == ' ') {
                endRow = row;
            }
        }
        Location endLocation = new Location(maze[0].length - 1, endRow, OrdinalDirection.EAST);
        logger.info("**** End Location: x = " + endLocation.x_pos + ", y = " + endLocation.y_pos);

        //Navigate maze (right hand on wall algorithm)
        String path = "";
        Boolean wallOnRight = false;
        Boolean wallInFront = false;
        Navigator navigator = new Navigator(startLocation, input_maze);
        while ((navigator.location.x_pos != endLocation.x_pos) || (navigator.location.y_pos != endLocation.y_pos)) {
            wallOnRight = navigator.checkWall(RelativeDirection.RIGHT);
            wallInFront = navigator.checkWall(RelativeDirection.FORWARD);
            //When wall on right and wall in front, turn left
            if (wallOnRight && wallInFront) {
                navigator.turnLeft();
                path += "L";
            } 
            //When wall on right and no wall in front, move forward
            else if (wallOnRight && !wallInFront) {
                navigator.moveForward();
                path += "F";
            } 
            //When no wall on right turn right and move forward
            else if (!wallOnRight){
                navigator.turnRight();
                path += "R";
                navigator.moveForward();
                path += "F";
            }
            //Terminate otherwise
            else {
                logger.error("**** Wall Lost!");
                break;
            }
        }
        logger.info("**** Reached the End of the Maze");

        Path solution = new Path(path);
        return solution;
    }

    public static void check(Path provided) {
        //Implement Path Checker
    }
}
