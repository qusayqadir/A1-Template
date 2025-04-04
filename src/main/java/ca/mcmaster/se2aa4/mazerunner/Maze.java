
package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public final class Maze {

    private static final Logger logger = LogManager.getLogger(Maze.class); 

    private List<List<Boolean>> maze = new ArrayList<>(); 

    public Maze(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("\t", " ");

                if (line.isEmpty()) continue; 

                if (line.length() < getCols()) {
                    line = String.format("%-" + getCols() + "s", line);
                }

                List<Boolean> newRow = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    newRow.add(c == ' ');
                }

                maze.add(newRow);
            }

            if (maze.isEmpty()) {
                throw new IllegalStateException("Maze file is empty or improperly formatted.");
            }
        } catch (IOException e) {
            logger.error("Error reading file", e);
            throw new RuntimeException("Error loading maze file: " + filepath);
        }

    }

    public int getRows(){
        return maze.size(); 
    }

    public int getCols() {
        if (maze.isEmpty()) {
            return 0; 
        }
        return maze.get(0).size(); 
    }

    public int[] mazeStartPosition() {
         
        if (maze.isEmpty() || maze.get(0).isEmpty()) {
            logger.error("Maze is empty"); 
        }

        int[] startPosition = new int[2];
        
        startPosition[1] = 0; 

        for ( int row = 0; row < this.getRows(); row++) {
            List<Boolean> currentRow = maze.get(row); 
            if (currentRow.get(0)) {
                startPosition[0] = row; 
                return startPosition; 
            }            
        }

        throw new IllegalStateException("No entry point on the west side of the maze.");
    }

    public int[] mazeExitPosition() {
        int [] exitPosition = new int[2]; 

        exitPosition[1] = this.getCols() - 1; 

        for ( int row = 0; row < this.getRows(); row++) {
            List<Boolean> currentRow = this.maze.get(row); 
            if (currentRow.get(this.getCols()-1)){
                exitPosition[0] = row; 
                return exitPosition; 
            }
        }
        throw new IllegalStateException("No entry point on the East side of the maze.");
    }

    public List<List<Boolean>> getMaze() {

        return this.maze; 

    }

    public boolean validMove(int row, int col) {
        return this.maze.get(row).get(col); 
    }
  
}