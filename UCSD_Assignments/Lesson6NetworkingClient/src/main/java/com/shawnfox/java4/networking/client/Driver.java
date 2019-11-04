package com.shawnfox.java4.networking.client;

import java.io.IOException;

public class Driver {

   public static void main(String[] args) {
      try {
         SocketTest.testNistTime();
         InetAddressTest.showLocalAddresses(args);
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

}
