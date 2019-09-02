
package com.shawndfox.javafxconcurrency.model;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * A java bean used as the data model for a table view that displays information
 * about files.
 * 
 * @author Shawn D. Fox
 */
public class FileProperties
{
   private StringProperty fileName;
   
   private StringProperty wordCount;
   
   private File theFile;
   
   /**
    * Bind the wordCount property to an ObservableValue such as the result of a Task
    * 
    * @param value an ObservableValue which will be bound
    */
   public void bindWordCount(ObservableValue<String> value) {
      wordCountProperty().bind(value);
      wordCount.addListener(new FilePropertiesListener(wordCount));
   }
   
   /**
    * Sets the file name property, and causes the construction of the underlying 
    * property if necessary.
    * @param value 
    */
   private void setFileName(String value) { 
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

   /**
    * @return the theFile
    */
   public File getTheFile()
   {
      return theFile;
   }

   /**
    * @param theFile the theFile to set
    */
   public void setTheFile(File theFile)
   {
      this.theFile = theFile;
      setFileName(theFile.getName());
   }
}

/**
 *
 * @author Shawn D. Fox
 */
class FilePropertiesListener implements ChangeListener
{
   private StringProperty source;
   
   public FilePropertiesListener(StringProperty property) {
      source = property;
   }
   
   @Override
   public void changed(ObservableValue observable, Object oldValue, Object newValue)
   {
      observable.removeListener(this);
      source.unbind();
   }
}

