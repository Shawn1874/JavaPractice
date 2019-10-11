package com.shawnfox.java4.regex;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test driver class for Lesson 2 regular expressions.
 * 
 * @author Shawn D. Fox
 *
 */
public class Driver {
   private static final String fileName = "neighbor-dump.txt";
   private static final String pandIdRegex = "PANID\\s*=\\s*[a-fA-F0-9]{4}";
   private static final String macPlusRfRssiRegex = "(?<mac>[a-fA-F0-9]{16}).*(?<rfRssi>-[0-9]+\\.[0-9]+)";

   public static void main(String[] args) {
      List<String> panId = new ArrayList<String>();
      List<String> mac = new ArrayList<String>();
      List<String> rfrssi = new ArrayList<String>();
      
      System.out.println(String.format("Processed the following input file: %n%s", fileName));
      System.out.println("Results are as follows");
      
      try {
         String input = Files.readString(Paths.get(fileName), Charset.defaultCharset());
         
         var panIdPattern = Pattern.compile(pandIdRegex);
         Matcher matcher = panIdPattern.matcher(input);
         
         while(matcher.find()) {
            //System.out.println(matcher.group());
            panId.add(matcher.group());
         }
         
         System.out.println(String.format("- List of PAN IDs (Total of %d)", panId.size()));
         for(String id : panId) {
            System.out.println(id);
         }
         
         var macAndRfRssi = Pattern.compile(macPlusRfRssiRegex);
         matcher = macAndRfRssi.matcher(input);
         
         while(matcher.find()) {
            mac.add(matcher.group("mac"));
            rfrssi.add(matcher.group("rfRssi"));
            //System.out.println(matcher.group(1));
            //System.out.println(matcher.group(2));
         }
         
         System.out.println(String.format("- List of MAC addresses (Total of %d)", mac.size()));
         for(String id : mac) {
            System.out.println(id);
         }
         
         System.out.println("- List of RF_RSSI_M values for each MAC address");
         for(int i = 0; i < mac.size(); ++i) {
            System.out.println(String.format("%s %s", mac.get(i), rfrssi.get(i)));
         }
      } 
      catch (Exception e1) {
         e1.printStackTrace();
      }
   }
}
