package com.shawnfox.java4.networking.server;

import java.io.IOException;

public class Driver {

   public static void main(String[] args) {
      try {
         //EchoServer.testServerSocket(args);
         ThreadedEchoServer.testThreadedEchoServer(args);
      }
      catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
