package ca.mcmaster.se2aa4.mazerunner.maze.standard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeParser;

public class StandardMazeFactory implements MazeFactory<StandardMaze> {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public StandardMaze build(String mazeFile) {

        logger.info("** Building a Standard Maze from the maze from file: " + mazeFile);
        
        MazeParser parser = new MazeParser(mazeFile);

        return new StandardMaze(parser.getMaze());
    }
}
