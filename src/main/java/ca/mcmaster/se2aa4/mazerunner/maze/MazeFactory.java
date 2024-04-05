package ca.mcmaster.se2aa4.mazerunner.maze;

public interface MazeFactory<MazeType extends Maze> {
    MazeType build(String mazeFile);
}
