
package ca.mcmaster.se2aa4.mazerunner;


import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input",true, "Path to maze.txt file ");
        options.addOption("p", "input", true, "User potential solution for maze "); 
        options.addOption("method","input", true, "User determines with Algorithm to use, i.e RightHandAlgo, BFS, DFS (only RightHandAlgo implemented)"); 

        CommandLineParser parser = new DefaultParser(); 
        CommandLine cmd;  

        try {

            cmd = parser.parse(options, args); 

            if (!cmd.hasOption("i")) {
                logger.error("No maze file path, use -i to select specific maze"); 
                return; 
            }

            String mazefilepath = cmd.getOptionValue("i"); 

            Maze maze = new Maze(mazefilepath); //------<<<<<< >>>>>>>>>> used to creat the ADS 


            if (cmd.getOptionValue("p")!= null){
                
                String algortihmMechanismRequest = cmd.getOptionValue("method"); 
                AlgorithmFactory factory = new AlgorithmFactory(); 
                PathFinder path = factory.getAlgorithmMechanism(algortihmMechanismRequest,  maze, true); 

                // PathFinder path = new RightHandAlgo(maze, true); 
                String userSolution = cmd.getOptionValue("p"); 

                /*
                validate: 
                1. expand user solution, which will be factorized form ( algorithm does not matter)
                2. create a new player and then have it run through the path and check if the player ends at the end position 
                */
                Boolean validPath = path.validate(userSolution);
                //user solution will be condensced ? 

                if(validPath) {
                    System.out.println("This is a correct path solution to this maze"); 
                }
                else {
                    System.out.println("This is an incorrect path solution to this maze"); 
                }
                
            }
            else {
                // solve the maze ? 
                logger.info("Finding valid solution for the maze path"); 
                String algortihmMechanismRequest = cmd.getOptionValue("m"); 
                AlgorithmFactory factory = new AlgorithmFactory(); 
                PathFinder path = factory.getAlgorithmMechanism(algortihmMechanismRequest,  maze, false); 
   
                  // pass in the maze object that can be used to find the path 
                String solution = path.findSolution(); 
                String factorizedPath = path.factorizedPath(solution);

                System.out.println("This is the solution to the maze: " + factorizedPath); 
                // will need to implement this solution 
                
            }

        } catch(ParseException e) {
            logger.error("Failed to find file path"); 
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\", e);
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
