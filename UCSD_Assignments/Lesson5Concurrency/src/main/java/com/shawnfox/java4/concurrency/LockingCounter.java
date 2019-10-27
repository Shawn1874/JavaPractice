/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class that managers the total character count by synchronizing the methods to 
 * set and get the value.
 * 
 * @author Shawn D. Fox
 *
 */
public class LockingCounter extends CharacterCounter {

   private static long totalCount = 0;
   private static ReentrantLock lock = new ReentrantLock();
   
   /**
    * @param javaFiles
    * @param classFiles
    */
   public LockingCounter(List<Path> javaFiles, List<Path> classFiles) {
      super(javaFiles, classFiles);
   }

   @Override
   public long getTotalCount() {
      return totalCount;
   }

   @Override
   public void updateTotalCount(long value) {
      lock.lock();
      totalCount += value;
      lock.unlock();
   }
}
