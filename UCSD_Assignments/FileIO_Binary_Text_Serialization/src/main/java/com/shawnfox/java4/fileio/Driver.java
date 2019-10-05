/**
 * 
 */
package com.shawnfox.java4.fileio;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shawn D. Fox
 *
 */
public class Driver {

   // Define a map of options with a description of the options that I can loop and print
   private static HashMap<String, String> options = new HashMap<>();
   
   /**
    * Construct a container of options and the descriptions
    */
   public static void buildOptions() {
      options.put("--help", "Display information about all options");
      options.put("--text", "Perform a demonstration of file IO by reading and writing text data");
      options.put("--binary", "Perform a demonstration of file IO by reading and writing binary data");
      options.put("--object", "Perform a demonstration of file IO by reading and writing object streams");
   }
   
   /**
    * This is the test driver class that can demonstrate File IO in 3 ways.  Processes
    * a command line argument to determine how to perform the file IO
    * 
    * @param args one of 4 values
    *    --help (displays help info)
    *    --text perform file IO using text reader and writer
    *    --binary perform file IO using text reader and writer
    *    --object perform file IO as object file
    * @throws IOException 
    */
   public static void main(String[] args) throws IOException {
      buildOptions();
      
      if(args.length != 1 || args[0].equals("--help")) {
         displayHelp();
      }
      else if(args[0].equals("--text")) {
         Demonstration demo = new TextFileDemo();
         demo.demonstrate();
      }
      else if(args[0].equals("--binary")) {
         Demonstration demo = new BinaryFileDemo();
         demo.demonstrate();
      }
      else if(args[0].equals("--object")) {
         Demonstration demo = new ObjectStreamDemo();
         demo.demonstrate();
      }
      else {
         displayHelp();
      }
   }
   
   /**
    * Display the help information for each of the program options.
    * 
    */
   public static void displayHelp() {
      for (Map.Entry<String, String> entry : options.entrySet()) {
         System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue()));
     }
   }
}
