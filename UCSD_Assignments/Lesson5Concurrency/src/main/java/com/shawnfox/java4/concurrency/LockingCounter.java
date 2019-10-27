/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A class that managers the total character count by synchronizing the methods to 
 * set and get the value.
 * 
 * @author Shawn D. Fox
 *
 */
public class LockingCounter extends CountingStrategy {

   private static long totalCount = 0;
   private static ReentrantLock lock = new ReentrantLock();
   
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
   
   /**
    * Reset the total count to 0.
    */
   @Override
   public void resetTotalCount() {
      lock.lock();
      totalCount = 0;
      lock.unlock();
   }
}
