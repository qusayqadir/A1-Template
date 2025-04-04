package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    North, South, East, West; 

    public Direction turnRight(){
        return switch (this) {
            case North -> Direction.East;
            case East -> Direction.South;
            case South -> Direction.West;
            case West -> Direction.North;
            default -> null;
        };
    }

    public Direction turnLeft(){
        return switch (this) {
            case North -> Direction.West;
            case East -> Direction.North;
            case South -> Direction.East;
            case West -> Direction.South;
            default -> null;
        };
    }
}