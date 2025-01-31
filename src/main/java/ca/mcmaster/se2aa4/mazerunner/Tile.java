package ca.mcmaster.se2aa4.mazerunner; 



public class Tile {

    private boolean isWall; 
    private Maze maze; 
    private row; 
    private col; 

    public Tile(int[] currPos, Maze maze) {
        this.isWall = false; //because the very first tile will be the start Pos 

        this.row = currPos[0]; 
        this.col = currPos[1];  

        this.maze = maze; 
    
    }

    public boolean isWall(int[] currPos, Direction currDir ) {
        
        switch(currDir){
            case North: 
                if (this.getTile(currPos[0] - 1, currPos[1]) == false) {
                    this.isWall = true; 
                }
                break; 
            case South: 
                if (this.getTile(currPos[0] + 1, currPos[1]) == false) {
                    this.isWall = true; 
                }
                break; 
            case East: 
                if (this.getTile(currPos[0], currPos[1] + 1) == false) {
                    this.isWall = true; 
                }
                break; 
            case West: 
                if (this.getTile(currPos[0], currPos[1] - 1) == false) {
                    this.isWall = true; 
                }
                break;
        }

        
        return this.isWall; 
    }

    public boolean getTile(int row, int col){
        return this.maze.get(row).get(col); 
    }

}