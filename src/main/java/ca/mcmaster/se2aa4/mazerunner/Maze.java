package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();

    char[][] maze;

    Maze(String maze_file) {
        logger.info("**** Reading the maze from file " + maze_file);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(maze_file));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    maze[row][idx] = line.charAt(idx);
                }
                row ++;
            }
            reader.close();
        } catch(Exception e) {
            logger.error("**** An error has occured while reading the file.");
        }
    }

    public char getPointInfo(Location location) {
        return this.maze[location.x_pos][location.y_pos];
    }
}
