/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shawndfox.javafxconcurrency.model;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn D. Fox
 */
public class FilePropertiesTests
{
   
   public FilePropertiesTests()
   {
   }
   
   public void testGettersAndSetters() {
      FileProperties fp = new FileProperties();
      
      
      File test6 = new File("Test6.txt");
      fp.setTheFile(test6);
      assertEquals("Test6.txt", fp.getFileName());
      assertTrue(test6 == fp.getTheFile());
      
      fp.setWordCount("150");
      assertEquals("150", fp.getWordCount());
   }
   
   public void testWordCountBinding() {
      FileProperties fp = new FileProperties();
      StringProperty wordCountProperty  = new SimpleStringProperty();
      wordCountProperty.setValue("25");
      assertEquals("25", wordCountProperty.get());
      
      fp.bindWordCount(wordCountProperty);
      assertEquals("25", fp.getWordCount());
      
      wordCountProperty.setValue("1500");
      assertEquals("1500", fp.getWordCount());
      
      // Verifies that after the binding only works once by design, and that the 
      // ChangeLisener causes the FileProperties wordCount property to be unbound
      // automatically after it is updated by the observable once.
      wordCountProperty.setValue("2500");
      assertEquals("1500", fp.getWordCount());
   }
}
