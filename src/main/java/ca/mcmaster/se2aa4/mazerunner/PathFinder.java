package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashSet;
import java.util.Set;

public abstract class PathFinder {
    protected static final Logger logger = LogManager.getLogger(RightHandAlgo.class);

    protected Maze maze;
    protected Player player;
    private Player validatePlayer; 
    protected StringBuilder solution;

    public PathFinder(Maze maze, boolean userInput) {
        this.maze = maze; 
        if(!userInput) {
            this.solution = new StringBuilder(); 
            this.player = new Player(maze.mazeStartPosition());
        }
        this.validatePlayer = new Player(maze.mazeStartPosition()); 
        if (userInput) {
            this.player = this.validatePlayer;
        }
        
    }

    public abstract String findSolution(); 
    
    public String factorizedPath(String solutionPath) {
        StringBuilder factorizedPath = new StringBuilder();
        int lengthSolution = solutionPath.length();
        int i = 0; 

        while ( i < lengthSolution) {
            char currentChar = solutionPath.charAt(i);
            int count = 1; 

            while (i + 1 < lengthSolution && solutionPath.charAt(i + 1) == currentChar) {
                i++;
                count++;
            }

            factorizedPath.append(count); 
            factorizedPath.append(currentChar);
            factorizedPath.append(" "); 
            i++;    
        }

        return factorizedPath.toString();
    }

    public String expandPath(String userSolution) {
        StringBuilder expandedPath = new StringBuilder();

        // Remove all spaces from the input
        userSolution = userSolution.replace(" ", "");

        // Check if the input is already in expanded form (no digits)
        if (!userSolution.matches(".*\\d.*")) {
            // Input is already expanded, return as-is
            return userSolution;
        }

        // Input is factorized, parse and expand it
        int i = 0;
        while (i < userSolution.length()) {
            // Extract the count (digits)
            int count = 0;
            while (i < userSolution.length() && Character.isDigit(userSolution.charAt(i))) {
                count = count * 10 + Character.getNumericValue(userSolution.charAt(i));
                i++;
            }

            // Extract the direction (non-digit character)
            if (i < userSolution.length()) {
                char direction = userSolution.charAt(i);
                i++;

                // Append the direction 'count' times
                for (int j = 0; j < count; j++) {
                    expandedPath.append(direction);
                }
            } else {
                // Invalid input (no direction after count)
                throw new IllegalArgumentException("Invalid factorized path: " + userSolution);
            }
        }

        return expandedPath.toString();
    }

    public boolean validate(String userSolution) {
        validatePlayer.setInitialDirection(); // Reset direction

        // Expand the factorized path
        String expandedPath = expandPath(userSolution);

        int lengthSolution = expandedPath.length();
        int i = 0;

        while (i < lengthSolution) {
            char move = expandedPath.charAt(i);

            // Process movement based on Right-Hand Rule logic
            if (move == 'R') {
                validatePlayer.playerTurnRight();
            } 
            else if (move == 'L') {
                validatePlayer.playerTurnLeft();
            } 
            else if (move == 'F') {
                // Check if the player can move forward
                if (canMove(validatePlayer.getDirection())) {
                    validatePlayer.playerMoveForward();
                } else {
                    return false; // Invalid move (hit a wall)
                }
            } 
            else {
                return false; // Invalid character in input
            }
            i++; // Move to the next character
        }

        // Final position should match the maze exit position
        int[] finalPos = validatePlayer.getPosition();
        int[] exitPos = maze.mazeExitPosition();

        return finalPos[0] == exitPos[0] && finalPos[1] == exitPos[1];
    }


    protected boolean canMove(Direction dir) {
        int[] nextPos = player.getNextPosition(dir);
        return isWithinBounds(nextPos) && maze.validMove(nextPos[0], nextPos[1]);
    }

    protected boolean isWithinBounds(int[] pos) {
        return pos[0] >= 0 && pos[0] < maze.getRows() && pos[1] >= 0 && pos[1] < maze.getCols();
    }

    protected boolean hasReachedEnd() {
        int[] playerPos = player.getPosition();
        int[] exitPos = maze.mazeExitPosition();
        return playerPos[0] == exitPos[0] && playerPos[1] == exitPos[1];
    }
}