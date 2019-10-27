/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A class that manages the total character count using an AtomicLong.
 * 
 * @author Shawn D. Fox
 *
 */
public class AtomicCharacterCounter extends CountingStrategy {

   private static AtomicLong totalCount = new AtomicLong(0L);
   
   @Override
   public long getTotalCount() {
      return totalCount.get();
   }
   
   @Override
   public void updateTotalCount(long value) {
      totalCount.addAndGet(value);
   }
   
   /**
    * Reset the total count to 0.
    */
   @Override
   public void resetTotalCount() {
      totalCount.set(0);
   }
}
