package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.services.MazeService;
import ca.mcmaster.se2aa4.mazerunner.services.RightHandSolver;

public class RightHandSolverTest {
    
    private final String testMaze = "./examples/direct.maz.txt";
    private final String rightHandMazeSolution = "F R 2F L 3F R F L F R F L 2F";

    @Test
    public void testRun() {
        StandardMazeFactory factory = new StandardMazeFactory();
        StandardMaze maze = factory.build(testMaze);
        MazeService service = new RightHandSolver(maze);
        String result = service.run();
        assertEquals(rightHandMazeSolution, result);
    }
}
