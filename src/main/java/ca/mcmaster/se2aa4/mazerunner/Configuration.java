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
    
    Configuration(String[] args) {
        //Add the ability to parse the -p flag

        //Uses Apache CLI to parse -i flag
        logger.info("**** Parsing Flags");
        Options options = new Options();
        options.addOption("-i", true, "The path to the maze file.");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            maze = cmd.getOptionValue("-i");

        } catch (ParseException pe) {
            logger.error("An error has occurred");
        }
    }
}
