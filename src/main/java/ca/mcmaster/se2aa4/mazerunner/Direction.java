package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    North, South, East, West; 

    public Direction turnRight(){
        switch(this) {
            case North: return Direction.East; 
            case East: return Direction.South; 
            case South: return Direction.West; 
            case West: return Direction.North; 
            default: return null; 
        }
    }

    public Direction turnLeft(){
        switch(this) {
            case North: return Direction.West; 
            case East: return Direction.North; 
            case South: return Direction.East; 
            case West: return Direction.South; 
            default: return null; 
        }
    }
}