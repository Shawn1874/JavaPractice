/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This abstract class provides the basis for demonstrating the management of data
 * shared across multiple threads.  A derived class must implement the methods to
 * set and get the total count of characters. It implements Callable so that
 * any number of instances can be constructed and executed by an ExecutorService.
 * 
 * @author Shawn D. Fox
 *
 */
public abstract class CharacterCounter implements Callable<Long> {

   private List<Path> javaFiles = new ArrayList<Path>();
   private List<Path> classFiles = new ArrayList<Path>();
   
   /**
    * Constructor which initializes the fields.
    * 
    * @param javaFiles - the list of .java files to process
    * @param classFiles - the list of .class files to process
    * @Throws - IllegalArgumentException if either or both of the lists are null references
    */
   public CharacterCounter(List<Path> javaFiles, List<Path> classFiles) {
      if(javaFiles == null || classFiles == null) {
         throw new IllegalArgumentException("A valid List object filled with paths is expected!");
      }
      
      this.javaFiles = javaFiles;
      this.classFiles = classFiles;
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
         updateTotalCount(localCount);
      }
      return Long.valueOf(count);
   }
   
   /**
    * The method returns the total count of characters within all of the files within 
    * the lists.
    * 
    * @return
    */
   abstract public long getTotalCount();
   
   /**
    * The method adds to the total count of characters.
    * 
    * @param value - the number of characters counted
    */
   abstract public void updateTotalCount(long value);
}
