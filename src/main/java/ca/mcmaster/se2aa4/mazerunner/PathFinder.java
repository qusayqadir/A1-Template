package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashSet;
import java.util.Set;

public class PathFinder {
    private static final Logger logger = LogManager.getLogger(PathFinder.class);

    private Maze maze;
    private Player player;
    private Player validatePlayer; 
    private StringBuilder solution;

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

    public String findSolution() {
        logger.info("Finding solution using Right-Hand Rule");

        // 🔹 Set initial direction to EAST (right)
        player.setInitialDirection();

        int maxSteps = 1000; // Prevent infinite loops
        int stepCount = 0;

        while (!hasReachedEnd() && stepCount < maxSteps) {
            stepCount++;

            // 🔹 Right-Hand Rule: Try turning right first
            Direction rightDir = player.getDirection().turnRight();
            if (canMove(rightDir)) {
                player.playerTurnRight();
                solution.append("R");
                player.playerMoveForward();
                solution.append("F");
            }
            // 🔹 If right is blocked, move forward if possible
            else if (canMove(player.getDirection())) {
                player.playerMoveForward();
                solution.append("F");
            }
            // 🔹 If forward is blocked, try turning left
            else if (canMove(player.getDirection().turnLeft())) {
                player.playerTurnLeft();
                solution.append("L");
                player.playerMoveForward();
                solution.append("F");
            }
            // 🔹 If all directions are blocked, turn around (180 degrees)
            else {
                player.playerTurnRight();
                solution.append("R");
                player.playerTurnRight();
                solution.append("R");
                player.playerMoveForward();
                solution.append("F");
            }

            // 🔥 Debugging Output
            logger.debug("Current Position:z`" + player.getPosition()[0] + "," + player.getPosition()[1] +
                         " | Direction: " + player.getDirection() + 
                         " | Path: " + solution.toString());
        }

      
        if (hasReachedEnd()) {
            logger.info("Maze solved! Path: " + solution.toString());
        } else {
            logger.warn("Maze could not be solved within " + maxSteps + " steps.");
        }

        return solution.toString();
    }

    private boolean canMove(Direction dir) {
        int[] nextPos = player.getNextPosition(dir);
        return isWithinBounds(nextPos) && maze.validMove(nextPos[0], nextPos[1]);
    }

    private boolean isWithinBounds(int[] pos) {
        return pos[0] >= 0 && pos[0] < maze.getRows() && pos[1] >= 0 && pos[1] < maze.getCols();
    }

    public boolean hasReachedEnd() {
        int[] playerPos = player.getPosition();
        int[] exitPos = maze.mazeExitPosition();
        return playerPos[0] == exitPos[0] && playerPos[1] == exitPos[1];
    }
    
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

    public boolean validate(String userSolution) {
        validatePlayer.setInitialDirection();
        int lengthSolution = userSolution.length();
        int i = 0; 

        while (i < lengthSolution) {
            char currentChar  = userSolution.charAt(i); 
            int count = 0; 
            int j = 0; 
            if (Character.isDigit(currentChar)) {

                count = Character.getNumericValue(currentChar); 
                i++;

            }
            else {
                count = 1; 
            }

            while ( j < count) {
                
                if (userSolution.charAt(i) == 'R') {
                    Direction newDirection = validatePlayer.getDirection().turnRight();    
                    if(canMove(newDirection)) {
                        validatePlayer.playerTurnRight(); 
                    }
                    else {
                        return false; 
                    }
    
                }
                else if (userSolution.charAt(i) == 'L') {
                    Direction newDirection = validatePlayer.getDirection().turnLeft();
                    if (canMove(newDirection)) {
                        validatePlayer.playerTurnLeft(); 
                    }
                    else {
                        return false; 
                    }
                    
                }
                else if (userSolution.charAt(i) == 'F') {
                    if(canMove(validatePlayer.getDirection())) {
                        validatePlayer.playerMoveForward(); 
                    }
                    else {
                        return false; 
                    }
                }
                j++;
            }
            i++;
        }

        return validatePlayer.getPosition()[0] == maze.mazeExitPosition()[0] && 
               validatePlayer.getPosition()[1] == maze.mazeExitPosition()[1];
    }
}