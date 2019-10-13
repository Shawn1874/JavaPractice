/**
 * 
 */
package com.shawnfox.java4.streams;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shawn D. Fox
 *
 */
public class Driver {

   public enum Parallel { TRUE, FALSE };
   
   private static String searchPattern = "0{8}[0-9a-fA-F]{8}";
   
   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("hello world!");
   }
   
   static long countOccurrences(String input, String regex, Parallel parallel) {
      var pattern = Pattern.compile(regex);
      
      if(parallel == Parallel.TRUE) {
         return pattern.matcher(input)
               .results()
               .parallel()
               .map(MatchResult::group)
               .count();
      }
      else {
         return pattern.matcher(input)
               .results()
               .map(MatchResult::group)
               .count();
      }
   }
   
   public static String getSearchPattern() {
      return searchPattern;
   }

}
