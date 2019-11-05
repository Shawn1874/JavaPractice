package com.shawnfox.java4.networking.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

@Command(name = "Lesson5NetworkingServer", mixinStandardHelpOptions = true, version = "1.0")
public class Driver implements Callable<Void>{

   @Option(names = { "-p", "--Port" }, required = true, description = "The port number to listen on")
   private static int port = 5000;
   
   public static void main(String[] args) {
      System.exit(new CommandLine(new Driver()).execute(args));
   }

   @Override
   public Void call() throws Exception {
      try {
         // establish server socket
         try (var s = new ServerSocket(port))
         {
            // wait for client connection
            System.out.println("waiting for connection");
            
            try (Socket incoming = s.accept())
            {
               OutputStream outStream = incoming.getOutputStream();
      
               try (BufferedReader in = new BufferedReader(new FileReader("TestData.txt")))
               {
                  var out = new PrintWriter(
                     new OutputStreamWriter(outStream, StandardCharsets.UTF_8),
                     true /* autoFlush */);
                  
                  // echo client input
                  String current;
                  while((current = in.readLine()) != null)
                  {
                     out.println(current);
                  }
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
