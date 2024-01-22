package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        
        //Walking Skeleton
        logger.info("** Starting Maze Runner");

        Configuration config = new Configuration(args);

        Maze maze = new Maze(config.maze);
        Path provided = new Path(config.path);
        
        if (provided.path == "") {
            Maze.check(provided);
        } else {
            Path solution = Maze.solve(maze);
            solution.print();
        }
        
        logger.info("** End of MazeRunner");
    }
}
