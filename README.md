# Maze Runner Assignment

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory. 
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
- The program can take a path as input and verify if it's a legit one.
- The program can use different methods to solve the maze.
- The program uses benchmarking to compare solution methods.

## How to run this software?

To build the program, simply package it with Maven:

```
mvn -q clean package 
```

Then run the mazeunner.jar file:
```
java -jar target/mazerunner.jar -i ./examples/small.maz.txt
```

#### Command line arguments

The program responds to the following flags:

- `-i MAZE_FILE` (Required) : Specifies the filename to be used. 
- `-p PATH_SEQUENCE` (Optional) : Activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze.
- `-m {righthand, bfs}` (Optional) : Specifies which path computation method to use. The default method is righthand.
- `-b {righthand, bfs}` (Optional) : Specifies a baseline to compare the selected algorithm to. Cannot be used without -m flag.

#### Examples

When no logs are activated, the programs prints the results to standard output.

Use `-i` to specify a maze file.
```
% java -jar target/mazerunner.jar -i ./examples/direct.maz.txt
% F R 2F L 3F R F L F R F L 2F
```
Use `-p` to verify a path.

If a given path is correct, the program prints the message `Correct Path!`.

```
% java -jar target/mazerunner.jar -i ./examples/direct.maz.txt -p "F R 2F L 3F R F L F R F L 2F"
% Correct Path!
```

If a given path is incorrect, the program prints the message `Incorrect Path!`.

```
% java -jar target/mazerunner.jar -i ./examples/direct.maz.txt -p 3F
% Incorrect Path
```

Use `-m` to select a different method.
```
% java -jar target/mazerunner.jar -i ./examples/direct.maz.txt -m bfs
% F R 2F L 4F R 2F L 2F
```

Use `-b` along with `-m` to compare the selected method against a specfied baseline.
```
% java -jar target/mazerunner.jar -i ./examples/direct.maz.txt -m bfs -b righthand
% Time spent loading the maze for bfs: 14.09 milliseconds.
% Time spent loading the maze for righthand: 3.76 milliseconds.
% Time spent exploring the maze for bfs: 6.01 milliseconds.
% Time spent exploring the maze for righthand: 3.99 milliseconds.
% Speedup = 1.13
```
