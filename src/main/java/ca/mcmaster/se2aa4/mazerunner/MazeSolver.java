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
        for (int row = 0; row <= maze.length; row++) {
            if (maze[row][0] == ' ') {
                startRow = row;
            } else {
                logger.error("/!\\ An error has occured /!\\");
                System.exit(0); //Terminate if not found
            }
        }
        Location startLocation = new Location(0, startRow, OrdinalDirection.EAST);

        //Find maze end location
        int endRow = 0;
        for (int row = 0; row <= maze.length; row++) {
            if (maze[row][maze[0].length] == ' ') {
                endRow = row;
            } else {
                logger.error("/!\\ An error has occured /!\\");
                System.exit(0); //Terminate if not found
            }
        }
        Location endLocation = new Location(maze[0].length, endRow, OrdinalDirection.EAST);

        //Navigate maze (right hand on wall algorithm)
        String path = "";
        Navigator navigator = new Navigator(startLocation, input_maze);
        while ((navigator.location.x_pos != endLocation.x_pos) && (navigator.location.y_pos != endLocation.y_pos)) {
            //When wall on right and wall in front, turn left
            if ((navigator.checkWall(RelativeDirection.RIGHT)) && (navigator.checkWall(RelativeDirection.FORWARD))) {
                navigator.turnLeft();
                path += "L";
            } 
            //When wall on right move forward an no wall in front, move forward
            else if ((navigator.checkWall(RelativeDirection.RIGHT)) && !(navigator.checkWall(RelativeDirection.FORWARD))) {
                navigator.moveForward();
                path += "F";
            } 
            //When no wall on right turn right
            else {
                navigator.turnRight();
                path += "R";
            }
        }

        Path solution = new Path(path);
        return solution;
    }

    public static void check(Path provided) {
        //Implement Path Checker
    }
}
