package ca.mcmaster.se2aa4.mazerunner.maze.graph;

import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class GraphMaze implements Maze {
    
    private Map<Integer, List<Integer>> adjList;

    public GraphMaze(Map<Integer, List<Integer>> adjList) {
        this.adjList = adjList;
    }
}
