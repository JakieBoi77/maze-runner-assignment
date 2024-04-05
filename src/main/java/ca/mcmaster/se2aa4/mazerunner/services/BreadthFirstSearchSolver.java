package ca.mcmaster.se2aa4.mazerunner.services;

import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMaze;

public class BreadthFirstSearchSolver implements MazeService {
    
    GraphMaze maze;

    public BreadthFirstSearchSolver(GraphMaze maze) {
        this.maze = maze;
    }

    @Override
    public void run() {

    }
}
