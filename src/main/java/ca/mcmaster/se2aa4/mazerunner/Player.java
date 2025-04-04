package ca.mcmaster.se2aa4.mazerunner; 

public class Player {

    private int row; 
    private int col; 
    private Direction currDir = Direction.East; // always start off facing east 


    public Player(int[] startPos) {
        this.row = startPos[0];
        this.col = startPos[1];  
    }

    public void playerMoveForward(){
        switch(this.currDir) {
            
            case North -> this.row--;
            case South -> this.row++;
            case East -> this.col++;
            case West -> this.col--; 
        } 

    }

    public void playerTurnRight() {
        this.currDir = this.currDir.turnRight(); 
    }

    public void playerTurnLeft(){
        this.currDir = this.currDir.turnLeft(); 
    }

    public int[] getPosition() {
        return new int[] {this.row, this.col}; 
    }   

    public void setInitialDirection() {
        
        this.currDir = Direction.East; 

    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col; 
    }

    public Direction getDirection() {
        return this.currDir; 
    }   


    public int[] getNextPosition(Direction dir) {
        int nextRow = this.row;
        int nextCol = this.col;

        switch(dir) {
            case North -> nextRow--;
            case South -> nextRow++;
            case East -> nextCol++;
            case West -> nextCol--; 
        }
        return new int[] {nextRow, nextCol}; 
    }
}