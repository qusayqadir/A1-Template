package  ca.mcmaster.se2aa4.mazerunner;


public class PlayerMoveForward implements Command {

    private Player player; 
    private int oldRow, oldCol; 

    public PlayerMoveForward(Player player){
        this.player = player; 

    }
    
    @Override
    public void execute(){
        oldRow = player.getPosition()[0]; 
        oldCol = player.getPosition()[1]; 

        player.playerMoveForward();
    }
    @Override
    public void undo(){
        player.setPosition(oldRow, oldCol); 
    }
}