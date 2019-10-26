/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Path;
import java.util.List;

/**
 * A class that managers the total character count by synchronizing the methods to 
 * set and get the value.
 * 
 * @author Shawn D. Fox
 *
 */
public class LockingCounter extends CharacterCounter {

   private static long totalCount = 0;
   
   /**
    * @param javaFiles
    * @param classFiles
    */
   public LockingCounter(List<Path> javaFiles, List<Path> classFiles) {
      super(javaFiles, classFiles);
   }

   @Override
   synchronized public long getTotalCount() {
      return totalCount;
   }

   @Override
   synchronized public void updateTotalCount(long value) {
      totalCount += value;
   }
}
