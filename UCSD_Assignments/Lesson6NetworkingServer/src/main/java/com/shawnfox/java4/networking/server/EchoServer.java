package com.shawnfox.java4.networking.server;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

/**
 * This program implements a simple server that listens to port 8189 and echoes back all
 * client input.
 * @version 1.22 2018-03-17
 * @author Cay Horstmann
 */
public class EchoServer
{
   public static void testServerSocket(int portNumber) throws IOException
   {
      System.out.println("entering testServerSocket");
      // establish server socket
      try (var s = new ServerSocket(portNumber))
      {
         // wait for client connection
         try (Socket incoming = s.accept())
         {
            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();
   
            try (var in = new Scanner(inStream, StandardCharsets.UTF_8))
            {
               var out = new PrintWriter(
                  new OutputStreamWriter(outStream, StandardCharsets.UTF_8),
                  true /* autoFlush */);
      
               out.println("Hello! Enter BYE to exit.");
      
               // echo client input
               var done = false;
               while (!done && in.hasNextLine())
               {
                  String line = in.nextLine();
                  out.println("Echo: " + line);
                  if (line.trim().equals("BYE")) done = true;
               }
            }
         }
      }
   }
}
