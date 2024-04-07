package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.MazeParser;
import ca.mcmaster.se2aa4.mazerunner.maze.standard.StandardMaze;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;

public class StandardMazeTest {
    
    private final String testMaze = "./examples/direct.maz.txt";
    private StandardMaze standardMaze = new StandardMaze((new MazeParser(testMaze)).getMaze());

    @Test
    public void testGetSizeX() {
        assertEquals(8, standardMaze.getSizeX());
    }

    @Test
    public void testGetSizeY() {
        assertEquals(7, standardMaze.getSizeY());
    }

    @Test
    public void testGetStart() {
        Coordinate start = standardMaze.getStart();
        Coordinate answer = new Coordinate(0, 1);
        assertEquals(answer.x(), start.x());
        assertEquals(answer.y(), start.y());
    }

    @Test
    public void testGetEnd() {
        Coordinate end = standardMaze.getEnd();
        Coordinate answer = new Coordinate(7, 5);
        assertEquals(answer.x(), end.x());
        assertEquals(answer.y(), end.y());
    }

    @Test
    void testIsWall() {
        assertTrue(standardMaze.isWall(new Coordinate(0, 0)));
        assertFalse(standardMaze.isWall(new Coordinate(0, 1)));
    }
}
