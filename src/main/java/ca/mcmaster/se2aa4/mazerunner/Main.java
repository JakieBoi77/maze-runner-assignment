package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger("** Starting Maze Runner");
        try {
            logger("**** Reading the maze from file " + args[0]);
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger("PASS ");
                    }
                }
                logger(System.lineSeparator());
            }
        } catch(Exception e) {
            System.err.println("/!\\ An error has occured /!\\");
        }
        logger("**** Computing path");
        logger("PATH NOT COMPUTED");
        logger("** End of MazeRunner");
    }
}
