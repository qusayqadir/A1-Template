package ca.mcmaster.se2aa4.assignment1; 

public class Player {

    private int row; 
    private int col; 
    private Direction currDir = Direction.East; // always start off facing east 


    public Player(int[] startPos) {
        this.row = startPos[0];
        this.col = startPos[1];  
    }

    public void moveFoward(){
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

    public void turnRight() {
        switch(this.currDir){ 
            case North:
                this.currDir = Directoin.East; 
                break;
            case South:
                this.currDir = Direction.West;
                break;
            case East:
                this.currDir = Direction.South;
                break;
            case West: 
                this.currDir = Direction.North;
                break; 
            
        }

    }

    public void turnLeft(){

    }

    public int[] getPosition() {
        return new int[] {this.row, this.col}; 
    }   

    //returns the direction the player is facing
    public Direction getDirection() {
        return this.currDir; 
    }   
}