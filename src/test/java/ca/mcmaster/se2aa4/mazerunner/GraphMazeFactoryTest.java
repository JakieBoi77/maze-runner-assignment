package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;

public class GraphMazeFactoryTest {

    private final String testMaze = "./examples/straight.maz.txt";
    
    @Test
    public void testBuild() {

        MazeFactory<StandardMaze> standardMazeFactory = new StandardMazeFactory();
        GraphMazeFactory graphMazeFactory = new GraphMazeFactory();

        StandardMaze standardMaze = standardMazeFactory.build(testMaze);
        GraphMaze graphMaze = graphMazeFactory.build(testMaze);
        Map<Integer, List<Integer>> adjList = graphMaze.getAdjList();

        // Verify that all non-wall coordinates in the standard maze have corresponding nodes in the graph maze
        for (int col = 0; col < standardMaze.getSizeX(); col++) {
            for (int row = 0; row < standardMaze.getSizeY(); row++) {
                Coordinate coord = new Coordinate(col, row);
                if (!standardMaze.isWall(coord)) {
                    int currentNode = row * standardMaze.getSizeX() + col;
                    assertTrue(adjList.containsKey(currentNode));
                }
            }
        }

        
    }
}
