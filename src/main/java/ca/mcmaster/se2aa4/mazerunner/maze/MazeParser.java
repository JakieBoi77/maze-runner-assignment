package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeParser {

    private static final Logger logger = LogManager.getLogger();

    private List<List<MazeElement>> maze;
    
    public MazeParser(String mazeFile) {
        logger.info("*********** Reading the maze from file " + mazeFile);
        
        List<List<MazeElement>> maze = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(mazeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<MazeElement> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        row.add(MazeElement.WALL);
                    } else if (line.charAt(i) == ' ') {
                        row.add(MazeElement.SPACE);
                    } else {
                        throw new Exception("Illegal character '" + line.charAt(i) + "' in the maze.");
                    }
                }
                maze.add(row);
            }
        } catch(Exception e) {
            System.err.println("MazeParser failed to build the maze.  Reason: " + e.getMessage());
            logger.error("MazeParser failed to build the maze.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
        }

        this.maze = maze;
        logger.info("*********** Maze successfully read.");
    }

    public List<List<MazeElement>> getMaze() {
        return this.maze;
    }
}
