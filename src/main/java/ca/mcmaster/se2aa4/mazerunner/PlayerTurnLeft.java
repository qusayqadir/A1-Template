package ca.mcmaster.se2aa4.mazerunner; 


public class PlayerTurnLeft implements Command{

    private Player player; 

    public PlayerTurnLeft(Player player) {
        this.player = player; 
    }
    @Override
    public void execute(){

        player.playerTurnLeft(); 
    }
    @Override 
    public void undo(){
        player.playerTurnRight(); 
    }
}