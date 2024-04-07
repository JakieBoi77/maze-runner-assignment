package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class PathTest {
    
    private final String unfactorizedPath = "FRFFLFFFFRFFLFFFFFFFFFFF";
    private final String factorizedPath = "F R 2F L 4F R 2F L 11F";

    @Test
    public void testFactorize() {
        Path path = new Path(unfactorizedPath);
        path.factorize();
        assertEquals(factorizedPath, path.getString());
    }

    @Test
    public void testUnfactorize() {
        Path path = new Path(factorizedPath);
        path.unfactorize();
        assertEquals(unfactorizedPath, path.getString());
    }

    @Test
    public void testFactorizeEmpty() {
        Path path = new Path("");
        path.factorize();
        assertEquals("", path.getString());
    }

    @Test
    public void testUnfactorizeEmpty() {
        Path path = new Path("");
        path.unfactorize();
        assertEquals("", path.getString());
    }
}
