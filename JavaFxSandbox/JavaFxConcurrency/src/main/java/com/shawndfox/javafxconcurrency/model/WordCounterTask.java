/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shawndfox.javafxconcurrency.model;

import java.io.File;
import java.util.Scanner;
import javafx.concurrent.Task;

/**
 * This is a JavaFx task that counts the words in the specified file using a Scanner
 * to read the file using the default white space delimiter.
 * 
 * @author Shawn D. Fox
 */
public class WordCounterTask extends Task<String>
{
   private final File input;
   
   /**
    * Constructor that requires a File object.
    * 
    * @param toAnalyze the File to analyze.  If the reference is null or the File is not
    * initialized to a readable file then the result of the call operation will indicate 
    * the error instead of a word count.
    */
   public WordCounterTask(File toAnalyze) {
      input = toAnalyze;
   }
   
   /**
    * The call method is invoked when the thread is started.
    * @return the word count of the input file or an error message
    */
   @Override
   protected String call()
   {
      String result;
      Integer counter = 0;
      if(input == null) {
         result = "A file was not specified!";
      }
      else {
         try(Scanner fileScanner = new Scanner(input)) {
            while(fileScanner.hasNext()) {
               counter++;
               fileScanner.next();
            }
            result = counter.toString();
         }
         catch(Exception e) {
            result = "error reading the file!";
         }
      }
      
      return result;
   }
}
