package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeElement;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeParser;

public class MazeParserTest {

    private final String testMaze = "./examples/straight.maz.txt";
    
    @Test
    public void testMazeParser() {
        MazeParser mazeParser = new MazeParser(testMaze);
        List<List<MazeElement>> maze = mazeParser.getMaze();

        assertEquals(5, maze.get(0).size());
        assertEquals(5, maze.size());

        assertEquals(MazeElement.WALL, maze.get(0).get(0));
        assertEquals(MazeElement.SPACE, maze.get(2).get(2));
        assertEquals(MazeElement.WALL, maze.get(4).get(2));
    }
}
