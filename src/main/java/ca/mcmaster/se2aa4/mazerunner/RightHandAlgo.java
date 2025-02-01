package ca.mcmaster.se2aa4.mazerunner; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList; 
import java.util.List;  



public class RightHandAlgo extends PathFinder{

    public RightHandAlgo(Maze maze, boolean userInput) {
        super(maze, userInput);
    }

    @Override
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

}