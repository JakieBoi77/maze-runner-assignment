package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.util.CardinalDirection;
import ca.mcmaster.se2aa4.mazerunner.util.Coordinate;

public class CoordinateTest {

    @Test
    public void testGetNorth() {
        Coordinate coordinate = new Coordinate(3, 5);
        Coordinate newCoord = coordinate.get(CardinalDirection.NORTH);
        assertEquals(3, newCoord.x());
        assertEquals(4, newCoord.y());
    }

    @Test
    public void testGetEast() {
        Coordinate coordinate = new Coordinate(3, 5);
        Coordinate newCoord = coordinate.get(CardinalDirection.EAST);
        assertEquals(4, newCoord.x());
        assertEquals(5, newCoord.y());
    }

    @Test
    public void testGetSouth() {
        Coordinate coordinate = new Coordinate(3, 5);
        Coordinate newCoord = coordinate.get(CardinalDirection.SOUTH);
        assertEquals(3, newCoord.x());
        assertEquals(6, newCoord.y());
    }

    @Test
    public void testGetWest() {
        Coordinate coordinate = new Coordinate(3, 5);
        Coordinate newCoord = coordinate.get(CardinalDirection.WEST);
        assertEquals(2, newCoord.x());
        assertEquals(5, newCoord.y());
    }
}
