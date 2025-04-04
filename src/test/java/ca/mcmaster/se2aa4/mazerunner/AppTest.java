package ca.mcmaster.se2aa4.mazerunner;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest{
    // ------ turningRightTestCases ------------ 
    @Test
    void testDirectionTurnRight(){
        assertEquals(Direction.East, Direction.North.turnRight());
        assertEquals(Direction.North, Direction.West.turnRight());
        assertEquals(Direction.South, Direction.East.turnRight()); 
        assertEquals(Direction.West, Direction.South.turnRight()); 
    }
    // ------ turningRightTestCases ------------ 
    @Test 
    void testDirectionTurnLeft(){
        assertEquals(Direction.North, Direction.East.turnLeft()); 
        assertEquals(Direction.East, Direction.South.turnLeft()); 
        assertEquals(Direction.South, Direction.West.turnLeft()); 
        assertEquals(Direction.West, Direction.North.turnLeft()); 
    }
    // -----MovePlayerForward(East) ------ 
    @Test 
    void testPlayerMovementsEast(){
        Player player = new Player(new int[]{5,5}); 
        player.playerMoveForward();
        assertArrayEquals(new int[] {5,6} , player.getPosition(), "Moving East should incrememnt col by 1"); 
    }
    // -----MovePlayerForward(NORTH) ------ 
    @Test 
    void testPlayerMovementsNorth(){
        Player player = new Player(new int[]{5,5}); 
        player.playerTurnLeft();
        player.playerMoveForward();
        assertArrayEquals(new int[] {4,5} , player.getPosition(), "Moving East should incrememnt col by 1"); 
    }
    // ---MazeReading------
    @Test
    void testMazeDimensionReading(){
        Maze maze1 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/direct.maz.txt");
        
        Maze maze2 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/small.maz.txt"); 
        
        Maze maze3 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/tiny.maz.txt"); 
        
        //Maze1
        assertEquals(8, maze1.getCols(), "Expected 8 columns");
        assertEquals(7, maze1.getRows(),"Expected 7 rows");

        //Maze 2  
        assertEquals(11, maze2.getCols(), "Expected 11 columns"); 
        assertEquals(11, maze2.getRows(), "Expeted 11 rows"); 

        //Maze3 
        assertEquals(7, maze3.getCols(), "Expected --- columns"); 
        assertEquals(7, maze3.getRows(), "Expeted --- rows"); 
    }
    
    // -----MazeStart&ExitPos----
    @Test
    void testMazeStartExitPos(){
        Maze maze1 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/direct.maz.txt");
        
        Maze maze2 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/small.maz.txt"); 
        
        Maze maze3 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/tiny.maz.txt"); 
        

        //Maze1
        assertArrayEquals(new int[] {1,0}, maze1.mazeStartPosition(), "Expected start row: 1 , start col: 1 ");
        assertArrayEquals(new int[] {5,7}, maze1.mazeExitPosition(),"Expected end row: 5, end col: 7");

        // //Maze 2  
        assertArrayEquals(new int[] {8,0}, maze2.mazeStartPosition(), "Expected start row: 8 , start col: 0 ");
        assertArrayEquals(new int[] {5,10}, maze2.mazeExitPosition(),"Expected end row: 5, end col: 10");

        // //Maze3 
        assertArrayEquals(new int[] {5,0}, maze3.mazeStartPosition(), "Expected start row: 5 , start col: 0 ");
        assertArrayEquals(new int[] {1,6}, maze3.mazeExitPosition(),"Expected end row: 1, end col: 6");
    }

    // -----MazeValidMove
    @Test
    void testValidMove(){
                
        Maze maze = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/direct.maz.txt");
        
        assertTrue(maze.validMove(1,1)); 
        assertTrue(maze.validMove(1,0)); 

        assertFalse(maze.validMove(1,2));
        assertFalse(maze.validMove(0,1));  
        assertFalse(maze.validMove(1,3));
        assertFalse(maze.validMove(0,2)); 

    }

    // ---Validate solution
    @Test
    void testValidateSolution(){
        
        Maze maze = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/straight.maz.txt");

        PathFinder path = new RightHandAlgo(maze, true); 
        
        boolean valid = path.validate("4F");
        boolean nonValid = path.validate("3F"); 
        assertTrue(valid); 
        assertFalse(nonValid);
    }

    // ---FactorizePath 
    @Test 
    void testFactorizePath(){
        
        Maze maze1 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/straight.maz.txt");
         
        Maze maze2 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/tiny.maz.txt"); 
        
        PathFinder path1 = new RightHandAlgo(maze1, true); 
        String factorized1 = path1.factorizedPath("FFFF"); 

        PathFinder path2 = new RightHandAlgo(maze2, true); 
        String factorized2 = path2.factorizedPath("FFFFFRRFFRFFRFFRRFFRFFRFFF"); 

        assertEquals("4F ", factorized1); 
        assertEquals("5F 2R 2F 1R 2F 1R 2F 2R 2F 1R 2F 1R 3F ", factorized2); 

    }

    // ---- ExpandPath 
    @Test
    void testExpandPath() {
        Maze maze1 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/straight.maz.txt");
        Maze maze2 = new Maze("/Users/qusayqadir/Documents/McMaster Soft 2nd/SOFT 2AA4/A1/A1-Template/examples/tiny.maz.txt"); 

        PathFinder path1 = new RightHandAlgo(maze1, true); 
        PathFinder path2 = new RightHandAlgo(maze2, true);
        
        String expanded1 = path1.expandPath("4F"); 
        String expanded2 = path2.expandPath("5F 2R 2F 1R 2F 1R 2F 2R 2F 1R 2F 1R 3F ");

        assertEquals("FFFF", expanded1.replaceAll("","")); 
        assertEquals("FFFFFRRFFRFFRFFRRFFRFFRFFF", expanded2.replaceAll("","")); 
    }
}
