/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.nio.file.Path;
import java.util.List;

/**
 * A class that manages a character counter without any synchronization at all.
 * This class will not work properly but demonstrates what happens when one 
 * doesn't properly synchronize or atomize operations that are utilized by
 * multiple threads.
 * 
 * @author Shawn D. Fox
 *
 */
public class UnsynchronizedCharacterCounter extends CharacterCounter {

   private static long totalCount = 0;
   
   /**
    * @param javaFiles
    * @param classFiles
    */
   public UnsynchronizedCharacterCounter(List<Path> javaFiles, List<Path> classFiles) {
      super(javaFiles, classFiles);
   }

   @Override
   public long getTotalCount() {
      return totalCount;
   }

   @Override
   public void updateTotalCount(long value) {
      totalCount += value;
   }
}
