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
            
            case North: 
                this.row--; 
                break; 
            case South: 
                this.row++; 
                break; 
            case East: 
                this.col++; 
                break; 
            case West: 
                this.col--; 
                break; 
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

    public Direction getDirection() {
        return this.currDir; 
    }   


    public int[] getNextPosition(Direction dir) {
        int nextRow = this.row;
        int nextCol = this.col;

        switch(dir) {
            case North: 
                nextRow--; 
                break; 
            case South: 
                nextRow++; 
                break; 
            case East: 
                nextCol++; 
                break; 
            case West: 
                nextCol--; 
                break; 
        }
        return new int[] {nextRow, nextCol}; 
    }
}