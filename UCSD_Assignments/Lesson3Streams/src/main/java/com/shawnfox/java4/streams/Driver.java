/**
 * 
 */
package com.shawnfox.java4.streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * @author Shawn D. Fox
 *
 */
public class Driver {

   public enum Parallel {
      TRUE, FALSE
   };

   private static String searchPattern = "0{8}[0-9a-fA-F]{8}";

   /**
    * @param args
    */
   public static void main(String[] args) {
      try {
         long before, after;
         int maxLoops = 200;
         int numLoops = 0;
         long lengthWithStream = 0;
         long lengthWithParallelStream = 0;
         long expectedOccurrences = 5;
         long actualOccurrences;
         
         StringBuilder builder = new StringBuilder();
         builder.append(Files.readString(Paths.get(args[0])));
         var input = builder.toString();
         
         // Loop up to 200 times in order to prevent an infinite loop.
         while(numLoops < maxLoops) {
           System.out.println(String.format("Try %d:", ++numLoops));
           System.out.println(String.format("String size: %d", input.length()));
           
           before = System.currentTimeMillis();
           actualOccurrences = countOccurrences(input, searchPattern, Parallel.TRUE);
           after = System.currentTimeMillis();
           lengthWithStream = after - before;
           System.out.println(String.format("Millisecs using stream: %d", lengthWithStream));
           assert(actualOccurrences == expectedOccurrences);
           
           before = System.currentTimeMillis();
           countOccurrences(input, searchPattern, Parallel.FALSE);
           after = System.currentTimeMillis();
           lengthWithParallelStream = after - before;
           System.out.println(String.format("Millisecs using parallel stream: %d", lengthWithParallelStream));
           assert(actualOccurrences == expectedOccurrences);
           
           if(lengthWithParallelStream < lengthWithStream) {
              System.out.println(String.format(
                    "Results: parallel stream was %d faster than stream", 
                    lengthWithStream - lengthWithParallelStream));
              break;
           }
           else {
              System.out.println(String.format(
                    "Results: stream was %d faster than parallel stream", 
                    lengthWithParallelStream - lengthWithStream));
              System.out.println("Doubling the string size and trying again.");
              builder.append(input);
              input = builder.toString();
              expectedOccurrences += 5;
           }
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Count the occurrences of the regular expression within the input.
    * 
    * @param input - the string to match the regex against
    * @param regex - the regex pattern
    * @param parallel - indicates whether or not to use a parallel stream
    * @return long - The number of matches within the input string
    */
   static long countOccurrences(String input, String regex, Parallel parallel) {
      var pattern = Pattern.compile(regex);

      if (parallel == Parallel.TRUE) {
         return pattern.matcher(input).results().parallel().map(MatchResult::group).count();
      }
      else {
         return pattern.matcher(input).results().map(MatchResult::group).count();
      }
   }

   public static String getSearchPattern() {
      return searchPattern;
   }

}
