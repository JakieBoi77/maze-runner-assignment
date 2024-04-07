package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.services.BreadthFirstSearchSolver;
import ca.mcmaster.se2aa4.mazerunner.services.PathChecker;
import ca.mcmaster.se2aa4.mazerunner.services.RightHandSolver;

public class ConfigurationTest {

    private final String testMaze = "./examples/direct.maz.txt";
    
    @Test
    public void testConstructorWithRightHandMethod() {
        String[] args = {"-i", testMaze, "-m", "righthand"};
        Configuration config = new Configuration(args);
        assertTrue(config.getSerivce() instanceof RightHandSolver);
    }

    @Test
    public void testConstructorWithBFSMethod() {
        String[] args = {"-i", testMaze, "-m", "bfs"};
        Configuration config = new Configuration(args);
        assertTrue(config.getSerivce() instanceof BreadthFirstSearchSolver);
    }

    @Test
    public void testConstructorWithNoMethod() {
        String[] args = {"-i", testMaze};
        Configuration config = new Configuration(args);
        assertTrue(config.getSerivce() instanceof RightHandSolver);
    }

    @Test
    public void testConstructorWithPathOption() {
        String[] args = {"-i", testMaze, "-p", "path.txt"};
        Configuration config = new Configuration(args);
        assertTrue(config.getSerivce() instanceof PathChecker);
    }
}
