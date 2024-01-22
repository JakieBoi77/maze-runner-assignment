package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();

    char[][] maze;

    Maze(String maze_file) {
        logger.info("Reading the maze from file " + maze_file);
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
            logger.error("An error has occured while reading the file.");
        }
    }

    public Path solve() {
        //Find maze start
        int startRow;
        for (int row = 0; row <= maze.length; row++) {
            if (maze[row][0] == ' ') {
                startRow = row;
            } else {
                logger.error("Start of maze not located.");
                System.exit(0); //Terminate if not found
            }
        }
        //Navigate maze (right hand on wall algorithm)


        Path path = new Path("Test");
        return path;
    }

    public static void check(Path provided) {
        //Implement Path Checker
    }

    public char getPointInfo(Point point) {
        char data = this.maze[point.x_pos][point.y_pos];
        return data;
    }
}
