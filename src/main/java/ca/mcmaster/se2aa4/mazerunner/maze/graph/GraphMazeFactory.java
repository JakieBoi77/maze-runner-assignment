package ca.mcmaster.se2aa4.mazerunner.maze.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.util.CardinalDirection;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;

public class GraphMazeFactory implements MazeFactory<GraphMaze> {

    private static final Logger logger = LogManager.getLogger();

    private int cols;
    private int rows;
    
    public GraphMaze build(String mazeFile) {
        
        MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
        StandardMaze maze = standardMazeFactory.build(mazeFile);

        logger.info("Converting Standard Maze to Graph Maze");

        this.cols = maze.getSizeX();
        this.rows = maze.getSizeY();

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {

                Coordinate coord = new Coordinate(col, row);
                if (!maze.isWall(coord)) {

                    int currentNode = getIndex(col, row);
                    List<Integer> neighbors = new ArrayList<>();

                    for (CardinalDirection direction : CardinalDirection.values()) {
                        Coordinate adj = coord.get(direction);
                        if (!maze.isWall(adj)) {
                            neighbors.add(adj.x(), adj.y());
                        }
                        adjList.put(currentNode, neighbors);
                    }

                }
            }
        }

        Coordinate start = maze.getStart();
        int startIndex = getIndex(start.x(), start.y());

        Coordinate end = maze.getEnd();
        int endIndex = getIndex(end.x(), end.y());

        return new GraphMaze(adjList, startIndex, endIndex);
    }

    // Helper function to select index
    private int getIndex(int col, int row) {
        return row * this.rows + col;
    }
}
