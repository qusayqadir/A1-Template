package ca.mcmaster.se2aa4.mazerunner; 


public class PlayerTurnRight implements Command{

    private Player player; 

    public PlayerTurnRight(Player player) {
        this.player  = player; 
    }

    @Override
    public void execute() {
        player.playerTurnRight(); 
    }    

    @Override 
    public void undo() {
        player.playerTurnLeft(); 
    }

    
}