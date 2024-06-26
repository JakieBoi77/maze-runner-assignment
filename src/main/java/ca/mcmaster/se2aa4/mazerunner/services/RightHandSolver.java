package ca.mcmaster.se2aa4.mazerunner.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.util.CardinalDirection;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class RightHandSolver implements MazeService {

    private static final Logger logger = LogManager.getLogger();

    StandardMaze maze;

    public RightHandSolver(Maze maze) {
        if (maze instanceof StandardMaze) {
            this.maze = (StandardMaze) maze;
        } else {
            throw new IllegalArgumentException("Maze must be an instance of StandardMaze");
        }
    }

    @Override
    public String run() {
        logger.info("****** Computing Path");

        Coordinate currentPos = this.maze.getStart();
        Coordinate endPos = this.maze.getEnd();
        CardinalDirection direction = CardinalDirection.EAST;

        String path = "";

        // Navigate maze (right hand on wall algorithm)
        while ((currentPos.x() != endPos.x()) || (currentPos.y() != endPos.y())) {
            
            // Check wall on the right
            boolean wallOnRight = this.maze.isWall(currentPos.get(direction.right()));
            // Check wall in front
            boolean wallInFront = this.maze.isWall(currentPos.get(direction));

            if (wallOnRight) {
                //When wall on right and wall in front, turn left
                if (wallInFront) {
                    logger.info("****** Turn Left");
                    direction = direction.left();
                    path += "L";
                }
                //When wall on right and no wall in front, move forward
                else {
                    logger.info("****** Move Forward");
                    currentPos = currentPos.get(direction);
                    path += "F";
                }
            } else {
                //When no wall on right turn right and move forward
                logger.info("****** Turn Right and Moving Forward");
                direction = direction.right();
                path += "R";
                currentPos = currentPos.get(direction);
                path += "F";
            }
            logger.info("****** New Position: x = " + currentPos.x() + ", y = " + currentPos.y());
        }

        logger.info("****** Reached the End of the Maze");

        Path solution = new Path(path);
        solution.factorize();
        return solution.getString();
    }
}
