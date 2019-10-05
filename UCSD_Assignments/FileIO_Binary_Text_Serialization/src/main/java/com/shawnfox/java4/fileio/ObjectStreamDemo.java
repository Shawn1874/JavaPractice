package com.shawnfox.java4.fileio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Demonstrates Object Serialization.  
 * 
 * Prompts the user for employee information, serializes an array of employees, 
 * deserializes the array, and the prints to the console.
 * 
 * @author Shawn D. Fox
 *
 */
public class ObjectStreamDemo extends Demonstration {

   /**
    * Demonstrate the concept of serialization.  Prompt the user for employee data, 
    * serialize it to a file, deserialize it back from the file, and then print the
    * data that was read from the file to the console.
    */
   @Override
   public void demonstrate() throws IOException {
      try (var consoleInput = new Scanner(System.in)) {
          
          Employee staff[] = readData(consoleInput, PromptUser.Yes);
          
          // save all employee records to the file employee.dat         
          try (var out = new ObjectOutputStream(new FileOutputStream("employeeStream.dat"))) 
          {
             out.writeObject(staff);
          }

       }
       catch (Exception e) {
          e.printStackTrace();
       }
      
      try (var in = new ObjectInputStream(new FileInputStream("employeeStream.dat")))
      {
         // retrieve all records into a new array
         var staff = (Employee[]) in.readObject();

         printEmployees(staff);
      } 
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}
