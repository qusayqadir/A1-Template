package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; 
import org.apache.commons.cli.*; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

public class PathFinder {

    private static final Logger logger = LogManager.getLogger(PathFinder.class);

    private Maze maze; 
    private Player player; 
    private String userSolution; 
    private Tile currTile; 

    
    private StringBuilder solution = new StringBuilder(); 


    public PathFinder(Maze maze) {
        this.maze = maze.getMaze(); 
        player = new Player(maze.mazeStartPosition()); 
        currTile = new Tile(maze.mazeStartPosition(), this.maze);
    }

    public String findSolution() { 
        logger.info("Finding Valid solution");
        // have a way to parse through the maze and append to solutionPath list. 
        // need to implement the logic to find the solution

        while (!hasReachedEnd()) { 
            if(this.canMoveForward()){
                player.moveForward(); 
                this.solution.append("F"); 
            }
        }

        return this.solution;  
    }

    public boolean validate(String userSolution) {
        // user soliution will be condensed, need to expand it, how ? 
    
        this.userSolution = userSolution;
        this.solution  = this.findSolution(); 

        if (this.userSolution.equals(this.solution)) {
            return true; 
        }
        else {
            return false; 
        }
    }

    public boolean hasReachedEnd() {
        // check if the player has reached the end of the maze
        if(player.getPosition() == maze.mazeExitPosition()) {
            return true; 
        }
        return false; 
    }

    /*
    only need to check if it can move forward, if it can't 
    then rotate, 
    and then check again can it move forward 
    */ 


    public boolean canMoveForward(){
        // checks if the next step is a wall need to know the diretion is facing 
        if(tile.isWall(player.getPosition()), player.getDirection()) {
            return false;
        }
        else{
            return true;
        }

    }

     


}
