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
 
                if (line.chatAt(idx) == "#") {
 
                    newRow.add(false); 
 
                }else if (line.chatAt(idx) == ' '){
 
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

  
}