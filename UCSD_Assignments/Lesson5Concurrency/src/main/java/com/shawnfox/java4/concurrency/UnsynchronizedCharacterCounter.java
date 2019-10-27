/**
 * 
 */
package com.shawnfox.java4.concurrency;

/**
 * A class that manages a character counter without any synchronization at all.
 * This class will not work properly but demonstrates what happens when one 
 * doesn't properly synchronize or atomize operations that are utilized by
 * multiple threads.
 * 
 * @author Shawn D. Fox
 *
 */
public class UnsynchronizedCharacterCounter extends CountingStrategy {

   private static long totalCount = 0;
   
   @Override
   public long getTotalCount() {
      return totalCount;
   }

   @Override
   public void updateTotalCount(long value) {
      totalCount += value;
   }
   
   /**
    * Reset the total count to 0.
    */
   @Override
   public void resetTotalCount() {
      totalCount = 0;
   }
}
