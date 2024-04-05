package ca.mcmaster.se2aa4.mazerunner.maze.graph;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;

public class GraphMazeFactory implements MazeFactory<GraphMaze> {
    
    public GraphMaze build(String mazeFile) {
        return new GraphMaze();
    }
}
