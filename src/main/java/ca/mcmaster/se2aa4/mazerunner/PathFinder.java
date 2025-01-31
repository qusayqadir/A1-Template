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

    private List<List<Boolean>> maze; 
    private String userSolution; 
    
    private StringBuilder solution = new StringBuilder(); 

    public PathFinder(Maze maze) {
        this.maze = maze.getMaze(); 
    }

    public String findSolution() { 
        logger.info("Finding Valid solution");
        // have a way to parse through the maze and append to solutionPath list. 
        this.solution  = "FFFF"; // just for the MVP 
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
}
