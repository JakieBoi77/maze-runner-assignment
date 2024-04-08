package ca.mcmaster.se2aa4.mazerunner.services;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class CompareMazeSolver implements MazeService {

    private static final Logger logger = LogManager.getLogger();

    private String mazeFile;
    private String method;
    private String baselineMethod;
    private String response;

    public CompareMazeSolver(String mazeFile, String method, String baselineMethod) {
        this.mazeFile = mazeFile;
        this.method = method;
        this.baselineMethod = baselineMethod;
        this.response = "";
    }

    @Override
    public String run() {
        try {
            logger.info("Calculating time to load maze.");
            MazeService service = initializeMazeService(method);
            MazeService baselineService = initializeMazeService(baselineMethod);

            logger.info("Calculating time to explore the maze.");
            long startTime = System.nanoTime();
            String stringPath = service.run();
            long endTime = System.nanoTime();
            this.response += String.format("Time spent exploring the maze for %s: %.2f milliseconds.\n", method, (endTime - startTime) / 1e6);

            startTime = System.nanoTime();
            String stringBaselinePath = baselineService.run();
            endTime = System.nanoTime();
            this.response += String.format("Time spent exploring the maze for %s: %.2f milliseconds.\n", baselineMethod, (endTime - startTime) / 1e6);

            logger.info("Calculating the speedup.");
            Path path = new Path(stringPath);
            Path baselinePath = new Path(stringBaselinePath);
            path.unfactorize();
            baselinePath.unfactorize();
            int pathLength = path.getString().length();
            int baselinePathLength = baselinePath.getString().length();
            float speedup = (float) baselinePathLength / pathLength;
            this.response += String.format("Speedup = %.2f", speedup);

        } catch (ParseException pe) {
            logger.error("******** Testing failed. Reason: " + pe.getMessage());
        }

        return response;
    }

    private MazeService initializeMazeService(String method) throws ParseException {
        MazeService service;
        MazeFactory<?> mazeFactory;

        switch (method) {
            case "righthand":
                mazeFactory = new StandardMazeFactory();
                break;
            case "bfs":
                mazeFactory = new GraphMazeFactory();
                break;
            default:
                throw new ParseException("Maze solving method '" + method + "' not supported.");
        }

        long startTime = System.nanoTime();
        Maze maze = mazeFactory.build(mazeFile);
        long endTime = System.nanoTime();
        this.response += String.format("Time spent loading the maze for %s: %.2f milliseconds.\n", method, (endTime - startTime) / 1e6);

        switch (method) {
            case "righthand":
                service = new RightHandSolver(maze);
                break;
            case "bfs":
                service = new BreadthFirstSearchSolver(maze);
                break;
            default:
                throw new ParseException("Maze solving method '" + method + "' not supported.");
        }

        return service;
    }
}
