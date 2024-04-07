package ca.mcmaster.se2aa4.mazerunner.maze.graph;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;

public class GraphMaze implements Maze {

    private static final Logger logger = LogManager.getLogger();
    
    private Map<Integer, List<Integer>> adjList;
    private StandardMaze maze;

    public GraphMaze(Map<Integer, List<Integer>> adjList, StandardMaze maze) {
        this.adjList = adjList;
        this.maze = maze;
        logger.info("************ Graph Maze created.");
    }

    public int getStartIndex() {
        Coordinate start = maze.getStart();
        int startIndex = getIndex(start.x(), start.y());
        return startIndex;
    }

    public int getEndIndex() {
        Coordinate end = maze.getEnd();
        int endIndex = getIndex(end.x(), end.y());
        return endIndex;
    }

    public List<Integer> getNeighbors(int index) {
        return adjList.get(index);
    }

    public int numberOfVertices() {
        return this.adjList.size();
    }

    public int getSizeX() {
        return this.maze.getSizeX();
    }

    public int getSizeY() {
        return this.maze.getSizeY();
    }

    public Map<Integer, List<Integer>> getAdjList() {
        return this.adjList;
    }

    private int getIndex(int col, int row) {
        return row * this.getSizeX() + col;
    }  
}
