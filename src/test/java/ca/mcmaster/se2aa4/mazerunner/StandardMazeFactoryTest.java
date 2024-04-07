package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;

public class StandardMazeFactoryTest {
    
    private final String testMaze = "./examples/straight.maz.txt";

    @Test
    public void testBuild() {
        StandardMazeFactory factory = new StandardMazeFactory();
        assertTrue(factory.build(testMaze) instanceof StandardMaze);
    }
}
