package com.shawnfox.java4.fileio;

import java.io.*;
import java.nio.charset.*;
import java.time.*;
import java.util.*;

/**
 * @author Shawn D. Fox
 */
public class TextFileDemo extends Demonstration
{
   /**
    * Demonstrate the concept of text file I/O.  Prompt the user for employee data, 
    * write it to a text file, read it back from the text file, and then print the
    * data that was read from the file to the console.
    */
   public void demonstrate() throws IOException
   {
      try (var consoleInput = new Scanner(System.in);
           var out = new PrintWriter("employee.dat", StandardCharsets.UTF_8)) {
         
         Employee staff[] = readData(consoleInput);
         writeData(staff, out);
      }
      
      // retrieve all records into a new array
      try (var in = new Scanner(
            new FileInputStream("employee.dat"), "UTF-8"))
      {
         Employee[] newStaff = readData(in);

         // print the newly read employee records
         for (Employee e : newStaff)
            System.out.println(e);
      }
   }

   /**
    * Writes all employees in an array to a print writer
    * @param employees an array of employees
    * @param out a print writer
    */
   private void writeData(Employee[] employees, PrintWriter out) 
         throws IOException
   {
      // write number of employees
      out.println(employees.length);

      for (Employee e : employees)
         writeEmployee(out, e);
   }
   
   /**
    * Writes employee data to a print writer
    * @param out the print writer
    */
   private void writeEmployee(PrintWriter out, Employee e)
   {
      out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
   }
}
