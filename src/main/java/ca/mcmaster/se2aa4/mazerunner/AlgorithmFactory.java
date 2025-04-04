package ca.mcmaster.se2aa4.mazerunner; 


public class AlgorithmFactory{
    
    public PathFinder getAlgorithmMechanism(String algortihmMechanism, Maze maze, Boolean request){
        PathFinder path = null; 

        if("RightHandAlgo".equals(algortihmMechanism)){
            path = new RightHandAlgo(maze, request); 
        }
        return path; 
    }
}