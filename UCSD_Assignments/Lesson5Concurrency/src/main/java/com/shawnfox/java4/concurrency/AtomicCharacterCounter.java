/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A class that manages the total character count using an AtomicLong.
 * 
 * @author Shawn D. Fox
 *
 */
public class AtomicCharacterCounter extends CharacterCounter {

   private static AtomicLong totalCount = new AtomicLong(0L);
   
   public AtomicCharacterCounter(List<Path> javaFiles, List<Path> classFiles) {
      super(javaFiles, classFiles);
   }
   
   @Override
   public long getTotalCount() {
      return totalCount.get();
   }
   
   @Override
   public void updateTotalCount(long value) {
      totalCount.addAndGet(value);
   }
}
