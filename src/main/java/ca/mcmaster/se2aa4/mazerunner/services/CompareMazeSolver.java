package ca.mcmaster.se2aa4.mazerunner.services;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class CompareMazeSolver implements MazeService {

    private static final Logger logger = LogManager.getLogger();

    private String mazeFile;
    private String method;
    private String baselineMethod;
    
    public CompareMazeSolver(String mazeFile, String method, String baselineMethod) {
        this.mazeFile = mazeFile;
        this.method = method;
        this.baselineMethod = baselineMethod;
    }

    @Override
    public String run() {

        MazeService service;
        MazeService baselineService;

        String response = "";

        long startTime, endTime;

        try {

            logger.info("Calculating time to load maze.");

            switch (method) {
                case "righthand":
                    MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
                    StandardMaze standardMaze;
                    
                    // Calculate time for loading the maze
                    startTime = System.nanoTime();
                    standardMaze = standardMazeFactory.build(mazeFile);
                    endTime = System.nanoTime();
                    response += String.format("Time spent loading the maze for %s: %.2f milliseconds.\n", method, (endTime - startTime) / 1e6);

                    service = new RightHandSolver(standardMaze);
                    break;

                case "bfs":
                    MazeFactory<GraphMaze> graphMazeFactory = new GraphMazeFactory();
                    GraphMaze graphMaze;

                    // Calculate time for loading the maze
                    startTime = System.nanoTime();
                    graphMaze = graphMazeFactory.build(mazeFile);
                    endTime = System.nanoTime();
                    response += String.format("Time spent loading the maze for %s: %.2f milliseconds.\n", method, (endTime - startTime) / 1e6);

                    service = new BreadthFirstSearchSolver(graphMaze);
                    break;
                default:
                    throw new ParseException("Maze solving method '" + method + "' not supported.");
            }

            switch (baselineMethod) {
                case "righthand":
                    MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
                    StandardMaze standardMaze;

                    // Calculate time for loading the maze
                    startTime = System.nanoTime();
                    standardMaze = standardMazeFactory.build(mazeFile);
                    endTime = System.nanoTime();
                    response += String.format("Time spent loading the maze for %s: %.2f milliseconds.\n", baselineMethod, (endTime - startTime) / 1e6);

                    baselineService = new RightHandSolver(standardMaze);
                    break;

                case "bfs":
                    MazeFactory<GraphMaze> graphMazeFactory = new GraphMazeFactory();
                    GraphMaze graphMaze;

                    // Calculate time for loading the maze
                    startTime = System.nanoTime();
                    graphMaze = graphMazeFactory.build(mazeFile);
                    endTime = System.nanoTime();
                    response += String.format("Time spent loading the maze for %s: %.2f milliseconds.\n", baselineMethod, (endTime - startTime) / 1e6);

                    baselineService = new BreadthFirstSearchSolver(graphMaze);
                    break;
                default:
                    throw new ParseException("Maze solving method '" + baselineMethod + "' not supported.");
            }

            logger.info("Calculating time to explore the maze.");

            //Calculate the time spent for each method
            startTime = System.nanoTime();
            String stringPath = service.run();
            endTime = System.nanoTime();
            response += String.format("Time spent exploring the maze for %s: %.2f milliseconds.\n", method, (endTime - startTime) / 1e6);
            startTime = System.nanoTime();
            String stringBaselinePath = baselineService.run();
            endTime = System.nanoTime();
            response += String.format("Time spent exploring the maze for %s: %.2f milliseconds.\n", baselineMethod, (endTime - startTime) / 1e6);

            logger.info("Calculating the speedup.");

            //Calculate the speedup
            Path path = new Path(stringPath);
            Path baselinePath = new Path(stringBaselinePath);
            path.unfactorize();
            baselinePath.unfactorize();
            int pathLength = path.getString().length();
            int baselinePathLength = baselinePath.getString().length();
            float speedup = (float) baselinePathLength / pathLength;
            response += String.format("Speedup = %.2f\n", speedup);

        } catch (ParseException pe) {
            logger.error("******** Testing failed.  Reason: " + pe.getMessage());
        }

        return response;
    }
}
