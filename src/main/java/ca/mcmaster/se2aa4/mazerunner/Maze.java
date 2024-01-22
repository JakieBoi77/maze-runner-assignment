package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    
    private static final Logger logger = LogManager.getLogger();

    Maze(String maze_file) {
        //Implement Maze Parser
        
        try {
            //Read the maze file
            logger.info("**** Reading the maze from file " + maze_file);
            BufferedReader reader = new BufferedReader(new FileReader(maze_file));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }
            reader.close();
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    public static Path solve(Maze maze) {
        //Implement Maze Solver
        Path path = new Path("Test");
        return path;
    }

    public static void check(Path provided) {
        //Implement Path Checker
    }
}
