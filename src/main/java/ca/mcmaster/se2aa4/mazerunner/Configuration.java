package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.*;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.*;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.*;
import ca.mcmaster.se2aa4.mazerunner.services.*;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class Configuration {
    
    private static final Logger logger = LogManager.getLogger();

    MazeService mazeService;
    
    public Configuration(String[] args) {
        logger.info("******** Parsing Flags");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(getParserOptions(), args);

            // Get maze from i flag
            String mazeFile = cmd.getOptionValue("i");
            

            // Check for p flag
            if (cmd.hasOption("p")) {
                // Initialize a PathChecker maze service
                logger.info("******** Initializing the path checker.");
                Path path = new Path(cmd.getOptionValue("p"));
                MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
                this.mazeService = new PathChecker(standardMazeFactory.build(mazeFile), path);
                return;
            }

            // Check for m flag
            else if (cmd.hasOption("m")) {
                String method = cmd.getOptionValue("m");
                switch (method) {

                    case "righthand":
                        // Initialize a RightHandSolver maze service
                        logger.info("******** Using righthand maze solving algorithm.");
                        MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
                        this.mazeService = new RightHandSolver(standardMazeFactory.build(mazeFile));
                        break;

                    case "bfs":
                        // Initialize a BreadthFirstSearchSolver maze service
                        logger.info("******** Using BFS maze solving algorithm.");
                        MazeFactory<GraphMaze> graphMazeFactory = new GraphMazeFactory();
                        this.mazeService = new BreadthFirstSearchSolver(graphMazeFactory.build(mazeFile));
                        break;

                    default:
                        throw new ParseException("Maze solving method '" + method + "' not supported.");
                }
            }
            
            // no m flag or p flag
            else {
                // Initialize a RightHandSolver maze service by default
                logger.info("******** Using default righthand maze solving algorithm.");
                MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
                this.mazeService = new RightHandSolver(standardMazeFactory.build(mazeFile));
            }
            
        } catch (ParseException pe) {
            logger.error("******** Configuration failed.  Reason: " + pe.getMessage());
            logger.error("******** PATH NOT COMPUTED");
        }
    }

    private Options getParserOptions() {
        Options options = new Options();
        
        Option fileOption = new Option("i", "input", true, "Specifies the filename of the maze file.");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption("p", "path", true, "Verfies the given path with the provided maze.");
        options.addOption("m", "method", true, "Selects a maze solving algorithm.");

        return options;
    }

    public MazeService getSerivce() {
        return this.mazeService;
    }
}
