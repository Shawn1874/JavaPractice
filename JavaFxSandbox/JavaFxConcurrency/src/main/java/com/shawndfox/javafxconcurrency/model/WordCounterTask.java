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
 *
 * @author Shawn D. Fox
 */
public class WordCounterTask extends Task<String>
{
   private File input;
   
   public WordCounterTask(File toAnalyze) {
      input = toAnalyze;
   }
   
   @Override
   protected String call() throws Exception
   {
      String result = "";
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
