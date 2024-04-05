package ca.mcmaster.se2aa4.mazerunner.maze.graph;

import java.util.List;
import java.util.Map;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class GraphMaze implements Maze {
    
    private Map<Integer, List<Integer>> adjList;
    private int start;
    private int end;

    public GraphMaze(Map<Integer, List<Integer>> adjList, int startIndex, int endIndex) {
        this.adjList = adjList;
        this.start = startIndex;
        this.end = endIndex;
    }

    public int getStartIndex() {
        return this.start;
    }

    public int getEndIndex() {
        return this.end;
    }

    public List<Integer> getNeighbors(int index) {
        return adjList.get(index);
    }
}
