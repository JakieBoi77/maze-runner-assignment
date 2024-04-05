package ca.mcmaster.se2aa4.mazerunner.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.util.CardinalDirection;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class PathChecker implements MazeService {

    private static final Logger logger = LogManager.getLogger();
    
    StandardMaze maze;
    String path;

    public PathChecker(StandardMaze inputMaze, Path providedPath) {
        this.maze = inputMaze;

        providedPath.unfactorize();
        this.path = providedPath.getString();
    }
    
    public void run() {
        logger.info("********** Validating Path");
        
        Coordinate currentPos = this.maze.getStart();
        Coordinate endPos = this.maze.getEnd();
        CardinalDirection direction = CardinalDirection.EAST;

        for (int i = 0; i < this.path.length(); i++) {
            
            try {  
                if (this.maze.isWall(currentPos)) {
                    logger.info("********** Entered a wall. Invalid Path.");
                    System.out.println("Incorrect Path!");
                    return;
                }
            } catch (IllegalStateException e) {
                logger.info("********** Exited the maze bounds. Invalid Path.");
                System.out.println("Incorrect Path!");
                return;
            }

            char nextMove = path.charAt(i);
            logger.info("********** Next Move: " + nextMove);
            switch (nextMove) {
                case 'F':
                    logger.info("********** Moving Forward");
                    currentPos = currentPos.get(direction);
                    break;
                case 'R':
                    logger.info("********** Turing Right");
                    direction = direction.right();
                    break;
                case 'L':
                    logger.info("********** Turning Left");
                    direction = direction.left();
                    break;
                default:
                    throw new IllegalStateException("Get next position failed. Unexpected value: " + nextMove);
            }
        }

        if ((currentPos.x() == endPos.x()) && (currentPos.y() == endPos.y())) {
            logger.info("********** Reached the end position!");
            System.out.println("Correct Path!");
        } else {
            logger.info("********** Did not make it to the end postion!");
            System.out.println("Incorrect Path!");
        }
    }
}
