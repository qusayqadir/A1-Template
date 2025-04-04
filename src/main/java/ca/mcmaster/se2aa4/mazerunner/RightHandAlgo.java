package ca.mcmaster.se2aa4.mazerunner; 

import java.lang.reflect.Executable;



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
                Command turnRight = new PlayerTurnRight(player); 
                turnRight.execute(); 
                solution.append("R");
                
                Command moveForward = new PlayerMoveForward(player); 
                moveForward.execute(); 
                solution.append("F");
            }
          
            else if (canMove(player.getDirection())) {
                Command moveForward = new PlayerMoveForward(player); 
                moveForward.execute(); 
                solution.append("F");
            }
            
            else if (canMove(player.getDirection().turnLeft())) {
                Command turnLeft = new PlayerTurnLeft(player); 
                turnLeft.execute(); 
                solution.append("L");

                Command moveForward = new PlayerMoveForward(player); 
                moveForward.execute(); 
                solution.append("F");
            }
            
            else {
                Command turnRight = new PlayerTurnRight(player); 
                turnRight.execute();
                solution.append("R");

                turnRight.execute();
                solution.append("R");

                Command moveForward  = new PlayerMoveForward(player); 
                moveForward.execute(); 
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