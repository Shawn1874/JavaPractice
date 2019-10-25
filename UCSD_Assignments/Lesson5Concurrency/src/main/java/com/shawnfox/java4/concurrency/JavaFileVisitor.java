/**
 * 
 */
package com.shawnfox.java4.concurrency;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a SimpleFileVisitor which tests each file extension and builds an array of files
 * that end with .java or .class
 * 
 * @author Shawn D. Fox
 *
 */
public class JavaFileVisitor extends SimpleFileVisitor<Path> {

   private List<Path> matchedPaths = new ArrayList<Path>();
   
   /**
    * @param Path - the file currently being visited
    * @param attrs - the attributes of the file
    */
   @Override
   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
      if(attrs.isRegularFile() && (file.toString().endsWith(".class") || file.toString().endsWith(".java"))) {
         matchedPaths.add(file);
      }
      return FileVisitResult.CONTINUE;
   }
   
   /**
    * Return a list of all files that matched the search expression.
    * 
    * @return
    */
   public List<Path> getMatchedPaths() {
      return matchedPaths;
   }

}
