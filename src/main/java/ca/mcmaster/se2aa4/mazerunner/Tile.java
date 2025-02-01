package ca.mcmaster.se2aa4.mazerunner; 



public class Tile {

    private boolean isWall; 
    private Maze maze; 
    private int row; 
    private int col; 

    public Tile(int[] currPos, Maze maze) {
        this.isWall = false; //because the very first tile will be the start Pos 

        this.row = currPos[0]; 
        this.col = currPos[1];  

        this.maze = maze; 
    
    }

    public boolean isWall(int[] currPos, Direction currDir ) {

        int tempRow = currPos[0]; 
        int tempCol = currPos[1];    

        switch(currDir) {
            case North: 
                tempRow--; 
                break; 
            case South: 
                tempRow++; 
                break; 
            case East: 
                tempCol++; 
                break; 
            case West: 
                tempCol--; 
                break; 
        }

        if (tempRow < 0 || tempRow >= maze.getRows() || tempCol < 0 || tempCol >= maze.getCols()) {
            return true; 
        }
        return !this.maze.validMove(tempRow, tempCol); 
    }

}