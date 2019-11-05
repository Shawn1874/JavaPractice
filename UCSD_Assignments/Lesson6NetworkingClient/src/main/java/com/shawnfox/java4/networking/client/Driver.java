package com.shawnfox.java4.networking.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

public class Driver  implements Callable<Void> {
   @ArgGroup(exclusive = true)
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
   
   public static void main(String[] args) {
      System.exit(new CommandLine(new Driver()).execute(args));
   }

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
