package ca.mcmaster.se2aa4.mazerunner.maze.standard;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeElement;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;

public class StandardMaze implements Maze {
    
    private static final Logger logger = LogManager.getLogger();

    List<List<MazeElement>> maze;
    Coordinate start;
    Coordinate end;

    public StandardMaze(List<List<MazeElement>> maze) {
        this.maze = maze;
        try {
            this.start = findStart();
            this.end = findEnd();
        } catch (Exception e) {
            logger.error("********* Failed to create Standard Maze.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }
        logger.info("********* Standard maze successfully built.");
    }

    private Coordinate findStart() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Coordinate coord = new Coordinate(0, i);
            if (!isWall(coord)) {
                logger.info("********* Start Location: x = " + coord.x() + ", y = " + coord.y());
                return coord;
            }
        }
        throw new Exception("Invalid maze, no start position found");
    }

    private Coordinate findEnd() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Coordinate coord = new Coordinate(getSizeX() - 1, i);
            if (!isWall(coord)) {
                logger.info("********* End Location: x = " + coord.x() + ", y = " + coord.y());
                return coord;
            }
        }
        throw new Exception("Invalid maze, no end position found");
    }

    public boolean isWall(Coordinate coord) {
        
        int xVal = coord.x();
        if (xVal < 0 || xVal > getSizeX() - 1) {
            throw new IllegalStateException("x-value, " + xVal + ", is out of bounds.");
        }

        int yVal = coord.y();
        if (yVal < 0 || yVal > getSizeY() - 1) {
            throw new IllegalStateException("y-value, " + yVal + ", is out of bounds.");
        }

        MazeElement elem = this.maze.get(yVal).get(xVal);
        return elem == MazeElement.WALL;
    }

    public Coordinate getStart() {
        return this.start;
    }

    public Coordinate getEnd() {
        return this.end;
    }

    public int getSizeX() {
        return this.maze.get(0).size();
    }

    public int getSizeY() {
        return this.maze.size();
    }

}
