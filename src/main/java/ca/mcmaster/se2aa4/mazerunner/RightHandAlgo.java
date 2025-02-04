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


        player.setInitialDirection();

        int maxSteps = 1000; 
        int stepCount = 0;

        while (!hasReachedEnd() && stepCount < maxSteps) {
            stepCount++;


            Direction rightDir = player.getDirection().turnRight();
            if (canMove(rightDir)) {
                player.playerTurnRight();
                solution.append("R");
                player.playerMoveForward();
                solution.append("F");
            }
          
            else if (canMove(player.getDirection())) {
                player.playerMoveForward();
                solution.append("F");
            }
            
            else if (canMove(player.getDirection().turnLeft())) {
                player.playerTurnLeft();
                solution.append("L");
                player.playerMoveForward();
                solution.append("F");
            }
            
            else {
                player.playerTurnRight();
                solution.append("R");
                player.playerTurnRight();
                solution.append("R");
                player.playerMoveForward();
                solution.append("F");
            }


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