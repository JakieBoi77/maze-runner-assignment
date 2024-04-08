package ca.mcmaster.se2aa4.mazerunner.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.graph.GraphMaze;
import ca.mcmaster.se2aa4.mazerunner.util.CardinalDirection;
import ca.mcmaster.se2aa4.mazerunner.util.Path;

public class BreadthFirstSearchSolver implements MazeService {

    private static final Logger logger = LogManager.getLogger();
    
    private Map<Integer, Boolean> marked;
    private Map<Integer, Integer> edgeTo;
    private int start;
    private int end;
    private int cols;
    private GraphMaze maze;

    public BreadthFirstSearchSolver(Maze maze) {
        this.marked = new HashMap<>();
        this.edgeTo = new HashMap<>();
        if (maze instanceof GraphMaze) {
            GraphMaze graphMaze = (GraphMaze) maze;
            this.start = graphMaze.getStartIndex();
            this.end = graphMaze.getEndIndex();
            this.cols = graphMaze.getSizeX();
            this.maze = graphMaze;
        } else {
            throw new IllegalArgumentException("Maze must be an instance of GraphMaze");
        }
        
    }

    @Override
    public String run() {

        logger.info("Running BFS");
        bfs(this.maze, this.start);

        Stack<Integer> graphPath = pathTo(this.end);

        if (graphPath == null) {
            logger.error("Path not found. Inavlid Maze.");
            return "";
        }

        logger.info("Cosntructing Maze Path");
        
        String mazePath = "";

        CardinalDirection currentDirection = CardinalDirection.EAST;
        int currentNode = graphPath.pop();
        while (!graphPath.isEmpty()) {
            int nextNode = graphPath.pop();
            CardinalDirection nextDirection = getDirection(currentNode, nextNode);
            logger.info("Current Direction = " + currentDirection + ", Next Direction = " + nextDirection);
            
            if (nextDirection.equals(currentDirection)) {
                logger.info("Move Forward");
                mazePath += 'F';
            } else if (nextDirection.equals(currentDirection.right())) {
                logger.info("Turn Right and Move Forward");
                currentDirection = currentDirection.right();
                mazePath += 'R';
                mazePath += 'F';
            } else if (nextDirection.equals(currentDirection.left())) {
                logger.info("Turn Left and Move Forward");
                currentDirection = currentDirection.left();
                mazePath += 'L';
                mazePath += 'F';
            }

            currentNode = nextNode;
        }
        logger.info("Maze path constructed!");

        Path path = new Path(mazePath);
        path.factorize();
        return path.getString();
    }

    private void bfs(GraphMaze maze, int start) {
        Queue<Integer> queue = new LinkedList<>();
        this.marked.put(start, true);
        queue.offer(start);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int adj : maze.getNeighbors(vertex)) {
                if (this.marked.get(adj) == null) {
                    this.edgeTo.put(adj, vertex);
                    this.marked.put(adj, true);
                    queue.offer(adj);
                }
            }
        }
    }

    private boolean hasPathTo(int vertex) {
        return this.marked.get(vertex) != null;
    }

    private Stack<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = vertex; x != this.start; x = edgeTo.get(x)) {
            path.push(x);
        }
        path.push(start);
        return path;
    }

    private CardinalDirection getDirection(int currentNode, int nextNode) {
        logger.info("Current Node: " + currentNode + ", Next Node: " + nextNode);
        
        if (nextNode == (currentNode + 1)) {
            return CardinalDirection.EAST;
        } else if (nextNode == (currentNode - 1)) {
            return CardinalDirection.WEST;
        } else if (nextNode == (currentNode + this.cols)) {
            return CardinalDirection.SOUTH;
        } else if (nextNode == (currentNode - this.cols)) {
            return CardinalDirection.NORTH;
        } else {
            logger.error("Invalid next node.");
            throw new IllegalStateException("Unexpected node values. Current Node: " + currentNode + ", Next Node: " + nextNode);
        }
    }
}
