package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.util.CardinalDirection;

public class CardinalDirectionTest {
    
    @Test
    public void testRight() {
        CardinalDirection north = CardinalDirection.NORTH;
        CardinalDirection east = CardinalDirection.EAST;
        CardinalDirection south = CardinalDirection.SOUTH;
        CardinalDirection west = CardinalDirection.WEST;

        CardinalDirection rightOfNorth = north.right();
        CardinalDirection rightOfEast = east.right();
        CardinalDirection rightOfSouth = south.right();
        CardinalDirection rightOfWest = west.right();

        assertEquals(CardinalDirection.EAST, rightOfNorth);
        assertEquals(CardinalDirection.SOUTH, rightOfEast);
        assertEquals(CardinalDirection.WEST, rightOfSouth);
        assertEquals(CardinalDirection.NORTH, rightOfWest);
    }

    @Test
    public void testLeft() {
        CardinalDirection north = CardinalDirection.NORTH;
        CardinalDirection east = CardinalDirection.EAST;
        CardinalDirection south = CardinalDirection.SOUTH;
        CardinalDirection west = CardinalDirection.WEST;

        CardinalDirection leftOfNorth = north.left();
        CardinalDirection leftOfEast = east.left();
        CardinalDirection leftOfSouth = south.left();
        CardinalDirection leftOfWest = west.left();

        assertEquals(CardinalDirection.WEST, leftOfNorth);
        assertEquals(CardinalDirection.NORTH, leftOfEast);
        assertEquals(CardinalDirection.EAST, leftOfSouth);
        assertEquals(CardinalDirection.SOUTH, leftOfWest);
    }
}
