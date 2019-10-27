/**
 * 
 */
package com.shawnfox.java4.concurrency;

/**
 * @author Shawn D. Fox
 *
 */
abstract public class CountingStrategy {

   
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
   
   /**
    * Reset the total count to 0.
    */
   abstract public void resetTotalCount();
}
