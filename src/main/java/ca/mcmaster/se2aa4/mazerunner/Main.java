package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            //Uses Apache CLI to parse -i flag
            logger.info("**** Parsing Flags");
            Options options = new Options();
            options.addOption("-i", true, "The path to the maze file.");
            CommandLineParser parser = new DefaultParser();
            String maze_file = "";
            try {
                CommandLine cmd = parser.parse(options, args);
                maze_file = cmd.getOptionValue("-i");

            } catch (ParseException pe) {
                logger.error("An error has occurred");
            }
            
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

        //Walking Skeleton
        logger.info("** Starting Maze Runner");

        Configuration config = new Configuration();
        config.load(args);

        Maze maze = new Maze(config.maze);
        Path provided_path = new Path(config.path);

        maze.solve();
        
        if (provided_path == "") {
            maze.displayPath();
        } else {
            maze.checkPath(provided_path);
        }
        
        logger.info("** End of MazeRunner");
    }
}
