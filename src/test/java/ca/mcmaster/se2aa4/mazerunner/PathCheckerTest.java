package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMazeFactory;
import ca.mcmaster.se2aa4.mazerunner.services.MazeService;
import ca.mcmaster.se2aa4.mazerunner.services.PathChecker;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class PathCheckerTest {
    
    private final String testMaze = "./examples/direct.maz.txt";
    private final String correctPath = "F R 2F L 3F R F L F R F L 2F";
    private final String incorrectPath = "F R 2F L";
    private final String correctResponse = "Correct Path!";
    private final String incorrectResponse = "Incorrect Path!";

    @Test
    public void testRunCorrectPath() {
        StandardMazeFactory factory = new StandardMazeFactory();
        StandardMaze maze = factory.build(testMaze);
        Path path = new Path(correctPath);
        MazeService service = new PathChecker(maze, path);
        String result = service.run();
        assertEquals(correctResponse, result);
    }

    @Test
    public void testRunIncorrectPath() {
        StandardMazeFactory factory = new StandardMazeFactory();
        StandardMaze maze = factory.build(testMaze);
        Path path = new Path(incorrectPath);
        MazeService service = new PathChecker(maze, path);
        String result = service.run();
        assertEquals(incorrectResponse, result);
    }
}
