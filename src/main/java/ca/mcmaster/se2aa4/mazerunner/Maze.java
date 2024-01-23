package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();

    char[][] maze_data;

    Maze(String maze_file) {
        logger.info("********** Reading the maze from file " + maze_file);

        List<char[]> maze_rows = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(maze_file));
            String line;
            while ((line = reader.readLine()) != null) {
                char[] row = line.toCharArray();
                maze_rows.add(row);
            }
            reader.close();
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            System.out.println(maze_data);
        }

        maze_data = new char[maze_rows.size()][];
        maze_data = maze_rows.toArray(maze_data);
    }

    public char getPointInfo(Location location) {
        return this.maze_data[location.y_pos][location.x_pos];
    }
}
