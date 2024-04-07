package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.services.BreadthFirstSearchSolver;
import ca.mcmaster.se2aa4.mazerunner.services.MazeService;

public class BreadthFirstSearchSolverTest {
    
    private final String testMaze = "./examples/direct.maz.txt";
    private final String bfsMazeSolution = "F R 2F L 4F R 2F L 2F";

    @Test
    public void testRun() {
        GraphMazeFactory factory = new GraphMazeFactory();
        GraphMaze maze = factory.build(testMaze);
        MazeService service = new BreadthFirstSearchSolver(maze);
        String result = service.run();
        assertEquals(bfsMazeSolution, result);
    }
}
