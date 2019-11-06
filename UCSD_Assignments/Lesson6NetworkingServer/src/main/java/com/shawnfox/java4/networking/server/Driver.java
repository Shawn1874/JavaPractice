package com.shawnfox.java4.networking.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * 
 * @author Shawn D. Fox
 *
 */
@Command(name = "Lesson6NetworkingServer", mixinStandardHelpOptions = true, version = "1.0")
public class Driver implements Callable<Void>{

   @Option(names = { "-p", "--Port" }, required = true, description = "The port number to listen on")
   private static int port = 0;
   
   /**
    * The entry point of the application.  It parses the command line arguments, and executes a 
    * callable task if the arguments or valid.
    * @param args
    */
   public static void main(String[] args) {
      System.exit(new CommandLine(new Driver()).execute(args));
   }

   /**
    * Creates a server socket on the specified port, and waits for a connection. When the 
    * connection is accepted, a file is read and pushed through the socket to the client as
    * UTF-8 characters.
    */
   @Override
   public Void call() throws Exception {
      try {
         // establish server socket
         try (var s = new ServerSocket(port))
         {
            // wait for client connection
            System.out.println("waiting for connection.");
            
            try (Socket incoming = s.accept())
            {
               OutputStream outStream = incoming.getOutputStream();
               var input = Driver.class.getClassLoader().getResourceAsStream("TestData.txt");
               try (BufferedReader in = new BufferedReader(
                     new InputStreamReader(input, StandardCharsets.UTF_8)))
               {
                  var out = new PrintWriter(
                     new OutputStreamWriter(outStream, StandardCharsets.UTF_8),
                     true /* autoFlush */);
                  
                  // Send the file to the client.
                  System.out.println("Sending the file TestData.txt.");
                  in.lines().forEach(line -> out.println(line));
                  System.out.println("Finished.  Exiting.");
               }
            }
         }
      }
      catch (IOException e) {
         e.printStackTrace();
      }

      return null;
   }

}
