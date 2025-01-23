package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; 
import java.apache.commons.cli.*; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;



public class Maze {

    private static final Logger logger = LogManager.getLogger(); 

    private List<List<Boolean>> maze = new ArrayList<>(); 

    public Maze(String filepath) {
  
        BufferedReader reader = new BufferedReader(new FileReader (filepath)); 
        String line; 

        while((line = reader.readLine()) != null ) {
 
            List<Boolean> newRow = new ArrayList<>(); 
 
            for ((int idx = 0; idx < line.length(); i++)) {
 
                if (line.charAt(idx) == "#") {
 
                    newRow.add(false); 
 
                }else if (line.charAt(idx) == ' '){
 
                    newRow.add(true); 
                }
            }
 
            maze.add(newRow); 
        } 

    }

    public int getRows(){
        if (!this.maze().isEmpty()) {
            
            return this.maze().size(); //
        }
        
    }

    public int getCols() {
        
        if(this.maze().get(0).isEmpty()){

            return this.maze().get(0).size(); 
        
        } 
    }

    public int[] mazeStartPosition() {
        int[] startPosition = new int[2]; 
        startPosition[1] = 0; 
        for ( int row = 0; row < this.maze.getRows(); row++) {
            List<Boolean> currentRow = maze.get(row); 
            if (currentRow.get(0)) {
                startPosition[0] = row; 
                return startPosition; 
            }            
            else{
                continue; 
            }
        }

        logger.info("No entry point on the west side of the maze"); 
        return 0; 
    }

    public int[] mazeExitPosition() {
        int [] exitPosition = new int[2]; 

        exitPosition[1] = this.maze.getCols() - 1; 

        for ( int row = 0; row < this.maze.getRows(); row++) {
            List<Boolean> currentRow = this.maze.get(row); 
            if (currentRow.get(this.maze.getCols()-1)){
                exitPosition[0] = row; 
                return exitPosition; 
            }
            else {
                continue; 
            }
        }
        logger.info("No exit point on th east side of the maze "); 
        return 0; 
    }

    public List<List<Boolean>> getMaze() {

        return this.maze; 

    }
  
}