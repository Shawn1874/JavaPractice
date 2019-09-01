
package com.shawndfox.javafxconcurrency.model;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn D. Fox
 */
public class WordCounterTaskTests
{
   
   public WordCounterTaskTests()
   {
   }
   
   public void testUnspecifiedFile() {
      WordCounterTask task = new WordCounterTask(null);
      String expectedResult = "A file was not specified!";
      String result = task.call();
      assertEquals(expectedResult, result);
   }
   
   public void testInaccessibleFile() {
      System.out.printf("Current directory is %s\n", System.getProperty("user.dir"));
      File inputFile = new File("test.txt");
      WordCounterTask task = new WordCounterTask(inputFile);
      String expectedResult = "error reading the file!";
      String result = task.call();
      assertEquals(expectedResult, result);
   }
   
   public void testWordCount() {
      System.out.printf("Current directory is %s\n", System.getProperty("user.dir"));
      File flatStanley = new File("FlatStanley281.txt");
      WordCounterTask task = new WordCounterTask(flatStanley);
      String expectedResult = "281";
      String result = task.call();
      assertEquals(expectedResult, result);
      
      File script = new File("ClubOfficerInstallationScript1065.txt");
      WordCounterTask task2 = new WordCounterTask(script);
      expectedResult = "1065";
      result = task2.call();
      assertEquals(expectedResult, result);
      
      File test6 = new File("Test6.txt");
      WordCounterTask task3 = new WordCounterTask(test6);
      expectedResult = "6";
      result = task3.call();
      assertEquals(expectedResult, result);
   }
}
