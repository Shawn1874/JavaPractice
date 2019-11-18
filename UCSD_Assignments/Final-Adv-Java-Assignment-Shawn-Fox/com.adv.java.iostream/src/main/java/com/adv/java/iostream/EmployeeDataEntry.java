package com.adv.java.iostream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import com.adv.java.xml.*;

public class EmployeeDataEntry {
   private Scanner keyboard = new Scanner(System.in);
   
   /**
    * The nameTest Predicate validates that the name entered is not null and 
    * valid according to the Employee.test
    */
   private IsValid<String> nameTest = (String name) -> { 
      boolean result = false;
      
      try {
         Employee.testName(name);
         result = true;
      } catch (IllegalArgumentException e) {
         System.out.println( 
                 "Invalid input! The string must be non-null, and contain whitespace, or the laters a-z or A-Z");
      }
      
      return result;
   };
   
   /**
    * The hourlySalaryTest Predicate validates that the salary value is 
    * Convertible into a BigDecimal object, and that it satisfies the 
    * requirements of the Employee class using its testHourlySalary method.
    */
   private static IsValid<String> hourlySalaryTest = (String salary) -> { 
      boolean result = false;
      try {
         BigDecimal value = new BigDecimal(salary);
         Employee.testHourlySalary(value);
         result = true;
      }
      catch (NumberFormatException e) {
         System.out.println(String.format("%s cannot be converted to BigDecimal", salary));
      }
      catch(InvalidSalaryException e) {
         System.out.println(e.getMessage());
      }
      return result;
   };
   
   /**
    * The hourlySalaryTest Predicate validates that the salary value is 
    * Convertible into a BigDecimal object, and that it satisfies the 
    * requirements of the Employee class using its testHoursWorked method.
    */
   private static IsValid<String> hoursWorkedTest = (String hours) -> { 
      boolean result = false;
      try {
         BigDecimal value = new BigDecimal(hours);
         Employee.testHoursWorked(value);
         result = true;
      }
      catch (NumberFormatException e) {
         System.out.println(String.format("%s cannot be converted to BigDecimal", hours));
      }
      catch(IllegalArgumentException e) {
         System.out.println(e.getMessage());
      }
      return result;
   };
   
   public ArrayList<Employee> readNewEmployees() {
      var newEmployees = new ArrayList<Employee>();
      String name = "";
      BigDecimal hourlySalary;
      BigDecimal hoursWorked;

      // Determine the number of employees that will be entered
      int numEmployees = getNumberOfEmployees();
      System.out.printf("You entered %s employees%n", numEmployees);
      
      
      for (int n = 0; n < numEmployees; n++) {
         // For each employee, ask for the name, hourly salary, and hours worked for the week
         System.out.println("Enter the information for employee " + (n + 1));
         name = getData("Enter the first name of the employee.", nameTest);
         hourlySalary = new BigDecimal(getData("Enter the hourly salary of the employee.", hourlySalaryTest));
         hoursWorked = new BigDecimal(getData("Enter the hours that the employee worked this week.", hoursWorkedTest));
         
         Employee newEmployee = new Employee(name, hourlySalary, hoursWorked);
         newEmployees.add(newEmployee);
         System.out.println("Added employee : " + newEmployee );
      }
      
      return newEmployees;
   }
   
   /**
    * Ask for the number of employees until an integer greater than or equal to 0 is entered.
    * 
    * @return the number of employees
    */
   private int getNumberOfEmployees() {
      int numEmployees = 0;
      
      do {
         System.out.println("Enter the number of employees");
         
         if(!keyboard.hasNextInt()) {
            System.out.println("Invalid number! Please enter a number.");
         }
         else {
            numEmployees = keyboard.nextInt();
            if(numEmployees <= 0) {
               System.out.println("Please enter a number >= 0.");
            }
         }
         keyboard.nextLine();
         
      } while(numEmployees <= 0);
      
      return numEmployees;
   }
   
   /**
    * Get data from the console entered by the user, and return the data as a string. Loop 
    * continuously until the user enters data that is valid according to the supplied predicate.
    * 
    * @param msg A message to print at the start of each loop.
    * @param isValid a predicate implementation that tests a string and returns a boolean indicating the validity
    * @return the String validated by the predicate
    */
   private String getData(String msg, IsValid<String> isValid) {
      String entry = "";
      
      do {
         System.out.println(msg);
         entry = keyboard.nextLine();
      } while(!isValid.test(entry));
      
      return entry;
   }
}
