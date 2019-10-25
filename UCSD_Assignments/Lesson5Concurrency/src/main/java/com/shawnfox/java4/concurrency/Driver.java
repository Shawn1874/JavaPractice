/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shawn D. FOx
 *
 */
public class Driver {

   /**
    * @param args
    */
   public static void main(String[] args) {
      try {
         Path dir = Paths.get(".");
         var visitor = new JavaFileVisitor();
         Files.walkFileTree(dir, visitor);
         System.out.println(visitor.getMatchedPaths().toString());
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
