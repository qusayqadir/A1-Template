
package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.commons.cli.*; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", "input",true, "Path to maze.txt file ");
        options.addOption("p", "input", true, "User potential solution for maze "); 

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

                PathFinder path = new PathFinder(maze); 
                String userSolution = cmd.getOptionValue("p"); 
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
                PathFinder path = new PathFinder(maze);
                  // pass in the maze object that can be used to find the path 
                // String solution = path.findSolution(); 
                System.out.println("This is the solution to the maze: "); 
                // will need to implement this solution 
                
            }

        } catch(ParseException e) {
            logger.error("Failed to find file path"); 
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
