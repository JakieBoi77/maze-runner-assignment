package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
    
    private static final Logger logger = LogManager.getLogger();

    String maze = "";
    String path = "";
    boolean path_provided = false;
    
    Configuration(String[] args) {

        logger.info("*** Parsing Flags");
        Options options = new Options();
        options.addOption("i", "input", true, "Specifies the filename of the maze file.");
        options.addOption("p", "path", true, "Verfies if a given path will solve the provided maze.");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            this.maze = cmd.getOptionValue("i");

            if (cmd.hasOption("p")) {
                this.path = cmd.getOptionValue("p");
                this.path_provided = true;
            }
            
        } catch (ParseException pe) {
            logger.error("*** An error has occurred");
        }
    }
}
