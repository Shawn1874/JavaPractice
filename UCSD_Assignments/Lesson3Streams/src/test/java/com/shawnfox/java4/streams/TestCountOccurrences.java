package com.shawnfox.java4.streams;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.shawnfox.java4.streams.Driver.Parallel;

class TestCountOccurrences {

   @Test
   void testCountOccurrences() {
      try {
         var input = Files.readString(Paths.get("JobResult_124432.txt"));
         assertEquals(5, Driver.countOccurrences(input, Driver.getSearchPattern(), Parallel.FALSE));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Test
   void testStreamCounter() {
      var pattern = Pattern.compile("[0-7]{3}");
      long count = pattern.matcher("123, 002, 800, 077, a12").results().count();
      assertEquals(3, count);
   }
   
   @Test
   void testRegexFilter() {
      List<String> numbers = Arrays.asList("123", "001", "abc", "0xa", "025");
      
      long count = numbers.stream()
         .filter(x -> x.matches("[0-7]{3}"))
         .count();
         
      assertEquals(3, count);
   }
   
   @Test 
   void testMatchResultsStream() {
      String input = "072abc077825abc";
      
      var pattern = Pattern.compile("[0-7]{3}");
      List<String> matches = pattern.matcher(input)
            .results()
            .map(MatchResult::group)
            .collect(Collectors.toList());
      
      assertEquals(2, matches.size());
   }
   
   @Test
   void testParallelStreamMatching() {
      try {
         var input = Files.readString(Paths.get("JobResult_124432.txt"));
         assertEquals(5, Driver.countOccurrences(input, Driver.getSearchPattern(), Parallel.TRUE));
      } 
      catch (Exception e) {
         e.printStackTrace();
      }
   }

}
