package com.shawnfox.java4.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Test driver class for Lesson 2 regular expressions.
 * 
 * @author Shawn D. Fox
 *
 */
public class Driver {

   private static final String pandIdRegex = "PANID\\s*=\\s*[a-fA-F0-9]{4}";
   private static final String macPlusRfRssiRegex = "(?<mac>[a-fA-F0-9]{16}).*(?<rfRssi>-[0-9]+\\.[0-9]+)";

   public static void main(String[] args) {
      try (BufferedReader input = Files.newBufferedReader(Paths.get("neighbor-dump.txt"))) {
         String line = null;
         while((line = input.readLine()) != null) {
            System.out.println(line);
         }
      } 
      catch (Exception e1) {
         e1.printStackTrace();
      }
      
      //TODO : scanner is probably not the way to go.  Better to read it into a file because I need
      // to use the MatchResult information to find groups.  I'm not ready for streams yet.
      try(Scanner in = new Scanner(Paths.get("neighbor-dump.txt"))) {
         var panIdPattern = Pattern.compile(pandIdRegex);
         //var matcher = pattern.matcher(input)
         
         // Match the PANIDs
         
           List<String> words =
           in.findAll(panIdPattern).map(MatchResult::group).collect(Collectors.toList()); 
           for(String word : words) { 
              System.out.println(word); 
              }
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
      
      try(Scanner in = new Scanner(Paths.get("neighbor-dump.txt"))) {
         // Match the MAC and RF_RSSI_I information
         var macPlusRfRssiPattern = Pattern.compile(macPlusRfRssiRegex);
         List<String> words = in.findAll(macPlusRfRssiPattern).map(MatchResult::group).collect(Collectors.toList());
         for(String word : words) {
            System.out.println(word);
         }
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
   }

}
