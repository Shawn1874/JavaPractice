package com.shawnfox.java4.fileio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * 
 * @author Shawn D. Fox
 *
 */
public abstract class Demonstration {
   
   abstract void demonstrate() throws IOException; 
   
   enum PromptUser { Yes, No };

   /**
    * Reads employee data from a buffered reader
    * @param in the scanner
    */
   private Employee readEmployee(Scanner in)
   {
      String line = in.nextLine();
      String[] tokens = line.split("\\|");
      String name = tokens[0];
      double salary = Double.parseDouble(tokens[1]);
      LocalDate hireDate = LocalDate.parse(tokens[2]);
      int year = hireDate.getYear();
      int month = hireDate.getMonthValue();
      int day = hireDate.getDayOfMonth();
      return new Employee(name, salary, year, month, day);
   }   

   /**
    * Reads an array of employees from a scanner
    * @param in the scanner
    * @return the array of employees
    */
   protected Employee[] readData(Scanner in, PromptUser promptUser)
   {
      // retrieve the array size
      if(promptUser == PromptUser.Yes) {
         System.out.println("Enter employee data in the form name|salary|hiredate");
      }
      
      System.out.println("Enter the number of employees");
      int n = in.nextInt();
      in.nextLine(); // consume newline
      var employees = new Employee[n];
      
      for (int i = 0; i < n; i++)
      {
         if(promptUser == PromptUser.Yes) {
            System.out.println("Enter employee data in the form name|salary|hiredate");
         }
         employees[i] = readEmployee(in);
      }
      return employees;
   }
}
