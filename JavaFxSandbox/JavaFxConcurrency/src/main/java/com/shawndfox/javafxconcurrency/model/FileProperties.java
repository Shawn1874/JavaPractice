
package com.shawndfox.javafxconcurrency.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A java bean used as the data model for a table view that displays information
 * about files.
 * 
 * @author Shawn D. Fox
 */
public class FileProperties
{
   private StringProperty fileName;
   
   /**
    * Sets the file name property, and causes the construction of the underlying 
    * property if necessary.
    * @param value 
    */
   public void setFileName(String value) { 
      fileNameProperty().set(value); 
   }
   
   /**
    * Gets the file name property, and causes the construction of the underlying 
    * property if necessary.
    * @param value 
    */
   public String getFileName() { 
      return fileNameProperty().get(); 
   }
   
   /**
    * Constructs the file name property if necessary, and then returns a reference to it.
    * @return 
    */
   public StringProperty fileNameProperty() { 
       if (fileName == null) {
          fileName = new SimpleStringProperty(this, "fileName");
       }
       return fileName; 
   }
   
   private StringProperty wordCount;
   
   /**
    * Sets the word count property, and causes the construction of the underlying 
    * property if necessary.
    * @param value 
    */
   public void setWordCount(String value) { 
      wordCountProperty().set(value); 
   }
   
   /**
    * Gets the word count property, and causes the construction of the underlying 
    * property if necessary.
    * @param value 
    */
   public String getWordCount() { 
      return wordCountProperty().get(); 
   }
   
   /**
    * Constructs the word count property if necessary, and then returns a reference to it.
    * @return 
    */
   public StringProperty wordCountProperty() { 
       if (wordCount == null) {
          wordCount = new SimpleStringProperty(this, "wordCount");
       }
       return wordCount; 
   }
}
