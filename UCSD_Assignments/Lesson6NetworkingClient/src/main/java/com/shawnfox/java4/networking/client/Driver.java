package com.shawnfox.java4.networking.client;

import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * This class demonstrates connecting to a server through a socket, and receiving data through
 * the input stream of the socket.
 * @author Shawn D. Fox
 *
 */
@Command(name = "Lesson6NetworkingClient", mixinStandardHelpOptions = true, version = "1.0")
public class Driver  implements Callable<Void> {
   @ArgGroup(exclusive = false)
   ConnectionOptions options = new ConnectionOptions();
   
   static class ConnectionOptions {
      @Option(names = { "-s", "--Server" }, 
              description = "The name of the host",
              required = false)
      String server = "localhost";

      @Option(names = { "-p", "--Port" }, 
            description = "the port number",
            required = true)
      int port = 0;
   }
   
   /**
    * The entry point of the application.  It parses the command line arguments, and executes a 
    * callable task if the arguments or valid.
    * @param args
    */
   public static void main(String[] args) {
      System.exit(new CommandLine(new Driver()).execute(args));
   }

   /**
    * Connect to a server using the specified options.  Read the data from the input stream,
    * and print it to the console.
    */
   @Override
   public Void call() throws Exception {
      try (var s = new Socket(options.server, options.port);
            var in = new Scanner(s.getInputStream(), StandardCharsets.UTF_8))
      {
         while (in.hasNextLine())
         {
            String line = in.nextLine();
            System.out.println(line);
         }
      }
      return null;
   }
}
