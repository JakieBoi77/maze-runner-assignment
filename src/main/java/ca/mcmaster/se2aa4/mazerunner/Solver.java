package ca.mcmaster.se2aa4.mazerunner;

public interface Solver {
    Path solveMaze(Maze input_maze);
    void check(Maze input_maze, Path provided_path);
}
