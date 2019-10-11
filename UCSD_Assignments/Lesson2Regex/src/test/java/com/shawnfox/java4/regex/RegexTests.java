package com.shawnfox.java4.regex;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class RegexTests {

   private final String regex = "0[Xx][0-9A-Fa-f]+";
   
   /**
    * Demonstrate compiling and matching in one step.
    */
   @Test
   void testPatternMatches() {
      assertTrue(Pattern.matches(regex, "0xa"));
      assertTrue(Pattern.matches(regex, "0XABCDEF90"));
      assertTrue(Pattern.matches(regex, "0x0123abf0"));
      assertFalse(Pattern.matches(regex, "x0123abf0"));
      assertFalse(Pattern.matches(regex, "0123abf0"));
      assertFalse(Pattern.matches(regex, "abcd"));
   }
   
   /**
    * Demonstrate the reuse of a compiled regex pattern.
    */
   @Test
   void testCompiledPattern() {
      var p = Pattern.compile(regex);
      var m = p.matcher("0x0123abf0");
      assertTrue(m.matches());
      m = p.matcher("0xFFFF");
      assertTrue(m.matches());
   }
   
   /**
    * Demonstrate the use of a scanner to find matches, and the use of a stream to build
    * the list of match results.
    */
   @Test
   void testScannerFindAll() {
      String pandIdRegex = "PANID\\s*=\\s*[a-fA-F0-9]{4}";
      try(Scanner in = new Scanner(Paths.get("neighbor-dump.txt"))) {
         var panIdPattern = Pattern.compile(pandIdRegex);
         
           List<String> words = in.findAll(panIdPattern)
                 .map(MatchResult::group)
                 .collect(Collectors.toList()); 
           
           assertEquals(words.size(), 3);
           
           for(String word : words) { 
              System.out.println(word); 
              }
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
   }

}
