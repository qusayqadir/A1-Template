
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

        CommandLineParser parser = new DefaultParser(); 
        CommandLine cmd;  

        try {

            cmd = parser.parse(options, args); 

            if (!cmd.hasOption("i")) {
                logger.error("No maze file path, use -i to select specific maze"); 
            }

            String filepath = cmd.getOptionValue("i"); 

            logger.info("**** Reading the maze from file " + filepath);  // needs to be removed so that it can find -i ?? 
            BufferedReader reader = new BufferedReader(new FileReader (filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.debug("Wall: "); 
                    } else if (line.charAt(idx) == ' ') {
                        logger.debug("Path: ");
                    }
                }
                logger.debug(System.lineSeparator());
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
