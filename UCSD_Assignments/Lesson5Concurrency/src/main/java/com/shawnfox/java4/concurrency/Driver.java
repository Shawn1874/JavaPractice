/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
         System.out.println(visitor.getJavaFiles().toString());
         System.out.println(visitor.getClassFiles().toString());

         var counter = new CharacterCounter(visitor.getJavaFiles(), visitor.getClassFiles());
         
         var executor = Executors.newFixedThreadPool(1);
         Future<Long> future = executor.submit(counter);
         System.out.println(String.format("Character count future: %d", future.get()));
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
