/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * This abstract class provides the basis for demonstrating the management of data
 * shared across multiple threads.  A derived class must implement the methods to
 * set and get the total count of characters. It implements Callable so that
 * any number of instances can be constructed and executed by an ExecutorService.
 * 
 * @author Shawn D. Fox
 *
 */
public class CharacterCounter implements Callable<Long> {

   private List<Path> javaFiles = new ArrayList<Path>();
   private CountingStrategy counter;
   
   /**
    * Constructor which initializes the fields.
    * 
    * @param javaFiles - the list of .java files to process
    * @param counter - a reference to a counting strategy object
    * @Throws - IllegalArgumentException if either parameter is a null reference
    */
   public CharacterCounter(List<Path> javaFiles, CountingStrategy counter) {
      if(javaFiles == null) {
         throw new IllegalArgumentException("A valid List object filled with paths is expected!");
      }
      
      if(counter == null) {
         throw new IllegalArgumentException("A valid strategy for managing the counter is expected!");
      }
      
      this.javaFiles = javaFiles;
      this.counter = counter;
   }
   
   /**
    * Count the number of characters across all files, and add that to the total
    * across all instances of this class.
    * 
    * @return Long - count of characters determined by this thread.
    */
   public Long call() throws Exception {
      long count = 0;
      
      for( var file : javaFiles) {
         var input = Files.newBufferedReader(file);
         long localCount = input.lines().mapToLong(s -> s.length()).sum();
         count += localCount;
         counter.updateTotalCount(localCount);
      }
      return Long.valueOf(count);
   }
}
