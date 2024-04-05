package ca.mcmaster.se2aa4.mazerunner.maze.standard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;

public class StandardMazeFactory implements MazeFactory<StandardMaze> {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public StandardMaze build(String mazeFile) {

        logger.info("** Reading the maze from file " + mazeFile);
        
        List<List<StandardElement>> maze = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<StandardElement> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        row.add(StandardElement.WALL);
                    } else if (line.charAt(i) == ' ') {
                        row.add(StandardElement.SPACE);
                    } else {
                        throw new Exception("Illegal character '" + line.charAt(i) + "' in the maze.");
                    }
                }
                maze.add(row);
            }
        } catch(Exception e) {
            System.err.println("StandardMazeFactory failed to build the maze.  Reason: " + e.getMessage());
            logger.error("StandardMazeFactory failed to build the maze.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        logger.info("** Maze successfully read");

        return new StandardMaze(maze);
    }
}
